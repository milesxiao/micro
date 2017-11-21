package me.tdcarefor.util.condition;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xxg on 16/9/9.
 * 属性名称必须为:get*方法
 * operator: <,<=,<>,!=,>=,>,=
 * 数组复合operator:
 *   arr_size_[operator] 数组的size
 *   arr_each_[operator] 数组中的每一条数据必须符合,数组size=0返回false;
 *   arr_exist_[operator] 数组中的有一条数据符合,数组size=0返回false;
 */
public class ConditionTreeAnalysis extends ConditionTreeAnalysisHelper{
    private Map<String,Method> methodDeclares;
    private Map<String,Map<String,Method>> listObjectsDeclares=new HashMap<>();
    public ConditionTreeAnalysis(Class<?> objectClass){
        methodDeclares=getMethodDeclares(objectClass,"get");
    }

    /** 测试条件树执行结果 */
    public boolean test(Object obj,ConditionTree conditionTree){
        boolean result;

        if(null==obj){
            return false;
        }

        if(null==conditionTree){
            return true;
        }

        String operator=conditionTree.getOperator().trim().toLowerCase();
        if("and".equals(operator)){
            result=operatorAnd(obj,conditionTree);
        }else if("or".equals(operator)){
            result=operatorOr(obj,conditionTree);
        }else{
            result=compare(methodDeclares,obj,conditionTree,operator);
        }

        return result;
    }

    /** And */
    public boolean operatorAnd(Object obj,ConditionTree conditionTree){
        boolean result=true;
        List<ConditionTree> brach=conditionTree.getConditionTrees();
        for(int i=0;i<brach.size();i++){
            if(!this.test(obj,brach.get(i))){
                result=false;
                break;
            }
        }
        return result;
    }

    /** Or */
    public boolean operatorOr(Object obj,ConditionTree conditionTree){
        boolean result=false;
        List<ConditionTree> brach=conditionTree.getConditionTrees();
        for(int i=0;i<brach.size();i++){
            if(this.test(obj,brach.get(i))){
                result=true;
                break;
            }
        }
        return result;
    }

    protected  boolean compare(Map<String,Method> objMethodDeclares,Object obj,ConditionTree conditionTree,String operator){
        boolean result=false;
        try{
            String propertyName=conditionTree.getPropertyName();
            if(!propertyName.startsWith("get")){
                propertyName="get"+propertyName.substring(0,1).toUpperCase()+propertyName.substring(1);
            }
            String value=conditionTree.getValue();

            /**非数组的处理*/
            if(!operator.startsWith("arr")){
                Method method=objMethodDeclares.get(propertyName);
                Object propertyValue=method.invoke(obj);
                result=compare(operator,getDataType(method.getReturnType()),propertyValue,value);
            }else{/**数组的处理*/
                String[] propertyNames=propertyName.split(".");
                Method method=objMethodDeclares.get(propertyNames[0]);
                Object propertyValue=method.invoke(obj);
                List<Object> list=(List<Object>)propertyValue;
                if(operator.startsWith("arr_size_")){
                    result=compare(operator.substring(9),"int",list.size(),value);
                }else if (list.size()>0){
                    Class<?> objectClass=list.get(0).getClass();
                    String classKey=objectClass.getName();
                    Map<String,Method> objMd=listObjectsDeclares.get(classKey);
                    if(null==objMd){
                        objMd=getMethodDeclares(objectClass,"get");
                        listObjectsDeclares.put(classKey,objMd);
                    }

                    Method _method=objMd.get(propertyNames[1]);
                    String _typeName=getDataType(_method.getReturnType());

                    if(operator.startsWith("arr_exist_")){
                        String arr_operator=operator.substring(10);
                        result=false;
                        for(int i=0;i<list.size();i++){
                            Object _value=_method.invoke(list.get(i));
                            if(compare(arr_operator,_typeName,_value,value)){
                                result=true;
                                break;
                            }
                        }
                    }else if(operator.startsWith("arr_each_")){
                        String arr_operator=operator.substring(9);
                        result=true;
                        for(int i=0;i<list.size();i++){
                            Object _value=_method.invoke(list.get(i));
                            if(!compare(arr_operator,_typeName,_value,value)){
                                result=false;
                                break;
                            }
                        }
                    }
                }
            }

        }catch (Exception e){

        }

        return result;
    }
}

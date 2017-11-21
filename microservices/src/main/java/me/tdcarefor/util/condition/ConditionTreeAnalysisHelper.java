package me.tdcarefor.util.condition;

import me.tdcarefor.util.Equal;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by xxg on 16/9/9.
 */
public class ConditionTreeAnalysisHelper {

    /*比较*/
    protected static boolean compare(String operator,String typeName,Object left,String right){
        boolean result=false;
        if(null!=left && null!=right){
            if("int".equals(typeName)){
                int leftValue=(int)left;
                int rightValue=Integer.parseInt(right);
                switch (operator){
                    case "<":result=leftValue < rightValue;break;
                    case "<=":result=leftValue <= rightValue;break;
                    case "=":result=leftValue == rightValue;break;
                    case "<>":result=leftValue != rightValue;break;
                    case "!=":result=leftValue != rightValue;break;
                    case ">":result=leftValue > rightValue;break;
                    case ">=":result=leftValue >= rightValue;break;
                }
            }

            if("float".equals(typeName)){
                float leftValue=(float)left;
                float rightValue=Float.parseFloat(right);
                switch (operator){
                    case "<":result=leftValue < rightValue;break;
                    case "<=":result=leftValue <= rightValue;break;
                    case "=":result=leftValue == rightValue;break;
                    case "<>":result=leftValue != rightValue;break;
                    case "!=":result=leftValue != rightValue;break;
                    case ">":result=leftValue > rightValue;break;
                    case ">=":result=leftValue >= rightValue;
                }
            }

            if("string".equals(typeName)){
                String leftStr=(String)left;
                if(null==leftStr){
                    leftStr="";
                }
                switch (operator){
                    case "=":result= Equal.eq(leftStr,right); break;
                    case "<>":result= !Equal.eq(leftStr,right); break;
                    case "!=":result= !Equal.eq(leftStr,right); break;
                    case "contain":result=leftStr.contains(right); break;
                    case "!contain":result=!leftStr.contains(right); break;
                }
            }
        }
        return result;
    }




    protected static Map<String,Method> getMethodDeclares(Class<?> objectClass,String start){
        Map<String,Method> declares=new HashMap<>();
        try {
            Method[] methods = objectClass.getDeclaredMethods();//获得类的方法集合
            //objectClass.getSuperclass().getDeclaredClasses()
            //遍历方法集合
            for(int i =0 ;i<methods.length;i++){
                Method method=methods[i];
                if(method.getName().startsWith(start)){
                    declares.put(method.getName(),method);
                }
            }

            for(Class<?> clazz = objectClass.getSuperclass() ; !clazz.getName().equals(Object.class.getName()) ; clazz = clazz.getSuperclass()) {
                Method[] parentMethods = clazz.getDeclaredMethods();
                for(int i =0 ;i<parentMethods.length;i++){
                    Method method=parentMethods[i];
                    if(method.getName().startsWith(start) && null==declares.get(method.getName())){
                        declares.put(method.getName(),method);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return declares;
    }

    protected static String getDataType(Class<?> t){
        String typeName=t.getSimpleName();
        if("Integer".equals(typeName) || "int".equals(typeName)){
            typeName="int";
        }else if("Float".equals(typeName) || "float".equals(typeName)){
            typeName="float";
        }else if("String".equals(typeName) || "string".equals(typeName)){
            typeName="string";
        }
        return typeName;
    }
}

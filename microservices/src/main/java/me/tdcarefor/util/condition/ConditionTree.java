package me.tdcarefor.util.condition;

import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.List;

/**
 * 条件树
 * Created by xxg on 16/9/7.
 */
public class ConditionTree {

    @ApiModelProperty(value ="操作符")
    private String operator;

    @ApiModelProperty(value ="属性名(必须get*,例如getOperator或getConditionTrees.getOperator)")
    private String propertyName;

    @ApiModelProperty(value ="值")
    private String value;

    @ApiModelProperty(value ="条件树.当操作符是and 或or 时")
    private List<ConditionTree> conditionTrees=new ArrayList<>();

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public List<ConditionTree> getConditionTrees() {
        return conditionTrees;
    }

    public void setConditionTrees(List<ConditionTree> conditionTrees) {
        this.conditionTrees = conditionTrees;
    }

}

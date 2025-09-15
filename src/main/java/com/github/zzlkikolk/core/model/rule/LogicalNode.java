package com.github.zzlkikolk.core.model.rule;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 逻辑节点
 */
@Setter
@Getter
@JsonTypeName("logical")
public class LogicalNode extends RootNode{

    private List<RootNode> children; // 逻辑节点

    private String operator;// 逻辑运算符

    public String getType() {
        return "logical";
    }
}

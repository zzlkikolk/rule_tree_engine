package com.github.zzlkikolk.core.model.rule.leaf;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.zzlkikolk.core.model.rule.RootNode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 信用分规则
 * {
 * "type":"riskScore",
 * "value":{
 * "score":750,
 * "operator":">="
 * }
 * }
 */
@Getter
@Setter
@JsonTypeName("riskScore")
public class RiskScoreNode extends RootNode {


    private Value value;

    public String getType() {
        return "riskScore";
    }

    @Data
    public static class Value {
        private Long score;

        private String operator;
    }
}

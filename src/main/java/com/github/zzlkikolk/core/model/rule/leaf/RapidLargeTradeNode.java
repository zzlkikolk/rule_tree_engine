package com.github.zzlkikolk.core.model.rule.leaf;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.zzlkikolk.core.model.rule.RootNode;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 瞬时大额交易类
 * {
 * "type":"rapidLargeTrade",
 * "value":{
 * "amountThreshold":750,
 * "timeWindowSeconds":300
 * }
 * }
 */
@Getter
@Setter
@JsonTypeName("rapidLargeTrade")
public class RapidLargeTradeNode extends RootNode {

    private Value value;

    public String getType() {
        return "rapidLargeTrade";
    }

    @Data
    public static class Value {
        // 交易金额
        private Double amountThreshold;

        // 时间窗口 秒
        private Long timeWindowSeconds;

    }
}

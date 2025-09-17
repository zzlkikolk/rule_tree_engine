package com.github.zzlkikolk.context.provider.risk;

import com.github.zzlkikolk.context.provider.ContextDataProvider;
import com.github.zzlkikolk.context.variables.ContextParam;
import lombok.Data;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/17 14:35
 */
public class RapidLargeTradeDataProvider implements ContextDataProvider<RapidLargeTradeDataProvider.RapidLargeTradeData> {


    @Override
    public String getType() {
        return "rapidLargeTrade";
    }

    @Override
    public RapidLargeTradeData load(ContextParam contextParam) {
        RapidLargeTradeData rapidLargeTradeData = new RapidLargeTradeData();
        rapidLargeTradeData.setAmountThreshold(50.00);
        rapidLargeTradeData.setTimeWindowSeconds(300L);
        return rapidLargeTradeData;
    }

    @Data
    public static class RapidLargeTradeData{
        private Double amountThreshold;

        private Long timeWindowSeconds;
    }
}

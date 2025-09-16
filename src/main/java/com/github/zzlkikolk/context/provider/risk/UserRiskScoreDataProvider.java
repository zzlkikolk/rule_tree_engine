package com.github.zzlkikolk.context.provider.risk;

import com.github.zzlkikolk.context.provider.ContextDataProvider;
import com.github.zzlkikolk.context.variables.ContextParam;
import com.github.zzlkikolk.context.variables.RiskControlContextParam;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/16 15:32
 */
public class UserRiskScoreDataProvider implements ContextDataProvider<Long> {
    @Override
    public String getType() {
        return "riskScore";
    }

    @Override
    public Long load(ContextParam contextParam) {
        RiskControlContextParam riskControlContextParam = (RiskControlContextParam) contextParam;
        if(riskControlContextParam.getUserId()==1)
            return 100L;
        return 59L;
    }
}

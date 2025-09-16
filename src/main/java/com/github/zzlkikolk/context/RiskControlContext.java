package com.github.zzlkikolk.context;

import com.github.zzlkikolk.context.provider.ContextDataProvider;
import com.github.zzlkikolk.context.variables.ContextParam;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 风控规则上下文
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/16 15:04
 */
public class RiskControlContext implements RuleExecutionContext{

    @Getter
    private final Long userId;

    private final ContextParam contextParam;
    private final Map<String, ContextDataProvider<?>> providers;

    public RiskControlContext(Long userId,
                              ContextParam contextParam,
                              List<ContextDataProvider<?>> providerList) {
        this.providers = new HashMap<>();
        this.userId = userId;
        this.contextParam=contextParam;
        providerList.forEach(p -> providers.put(p.getType(), p));
    }

    public RiskControlContext(Long userId,
                              ContextParam contextParam,
                              Map<String, ContextDataProvider<?>> providers) {
        this.userId = userId;
        this.contextParam=contextParam;
        this.providers=providers;
    }


    @Override
    public <T> T getData( String type, Class<T> ClassType) {
        ContextDataProvider<?> provider = providers.get(type);
        if (provider == null) {
            throw new IllegalArgumentException("No provider for key: " + type);
        }
        Object value = provider.load(contextParam);

        return (T) value;
    }
}

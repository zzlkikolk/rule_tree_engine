package com.github.zzlkikolk.context;

import com.github.zzlkikolk.context.variables.ContextParam;

/**
 * 规则上下文
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/16 15:03
 */
public interface RuleExecutionContext {

    /**
     * 根据规则类型获取所需的上下文数据
     * @param type 规则类型
     * @param ClassType 响应数据类型
     * @return 规则所需的上下文参数类型
     * @param <T>
     */
    <T> T getData(String type, Class<T> ClassType);
}

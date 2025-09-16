package com.github.zzlkikolk.context.provider;

import com.github.zzlkikolk.context.variables.ContextParam;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/16 15:07
 */
public interface ContextDataProvider<T> {

    /**
     * 上下文数据对应规则类型名称
     * @return 类型名词
     */
    String getType();

    /**
     * 加载数据
     * @return 数据
     */
    T load(ContextParam contextParam);
}

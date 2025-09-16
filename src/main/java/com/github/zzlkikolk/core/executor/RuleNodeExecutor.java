package com.github.zzlkikolk.core.executor;

import com.github.zzlkikolk.context.RuleExecutionContext;
import com.github.zzlkikolk.core.model.rule.RootNode;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/16 15:37
 */
public interface RuleNodeExecutor {

    boolean execute(RuleExecutionContext context, RootNode rootNode);

    String getNodeType();
}

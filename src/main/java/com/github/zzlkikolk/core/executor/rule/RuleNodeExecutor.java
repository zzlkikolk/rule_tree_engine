package com.github.zzlkikolk.core.executor.rule;

import com.github.zzlkikolk.context.RuleExecutionContext;
import com.github.zzlkikolk.core.model.rule.RootNode;

/**
 * @author zhangzhilin
 */
public interface RuleNodeExecutor {

    boolean execute(RuleExecutionContext context, RootNode rootNode);

    String getNodeType();
}

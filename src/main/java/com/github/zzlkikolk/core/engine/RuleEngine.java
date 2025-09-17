package com.github.zzlkikolk.core.engine;

import com.github.zzlkikolk.context.variables.ContextParam;
import com.github.zzlkikolk.core.executor.rule.RuleNodeExecutor;
import com.github.zzlkikolk.core.executor.rule.RuleNodeExecutorFactory;
import com.github.zzlkikolk.core.model.rule.RootNode;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * @author zhangzhilin
 * @version 2025
 * @date 2025/09/16 15:49
 */

public interface RuleEngine {

    boolean execute(List<RootNode> list, ContextParam contextParam);
}

package com.github.zzlkikolk.core.parser.rule;

import com.github.zzlkikolk.core.model.rule.RootNode;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动作类型注册中心
 */
public class RootNodeTypeRegistry {

    private static final Map<String, Class<? extends RootNode>> TYPE_MAP = new ConcurrentHashMap<>();

    public static void register(String typeName, Class<? extends RootNode> rootNodeClass) {
        TYPE_MAP.put(typeName, rootNodeClass);
    }

    public static Class<? extends RootNode> getRootNodeClass(String typeName) {
        return TYPE_MAP.get(typeName);
    }

    public static Set<String> getAllRegisteredTypes() {
        return TYPE_MAP.keySet();
    }
}

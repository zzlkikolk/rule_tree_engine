package com.github.zzlkikolk.core.parser.actions;

import com.github.zzlkikolk.core.model.actions.Actions;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动作类型注册中心
 */
public class ActionTypeRegistry {

    private static final Map<String, Class<? extends Actions>> TYPE_MAP = new ConcurrentHashMap<>();

    public static void register(String typeName, Class<? extends Actions> actionClass) {
        TYPE_MAP.put(typeName, actionClass);
    }

    public static Class<? extends Actions> getActionClass(String typeName) {
        return TYPE_MAP.get(typeName);
    }

    public static Set<String> getAllRegisteredTypes() {
        return TYPE_MAP.keySet();
    }
}

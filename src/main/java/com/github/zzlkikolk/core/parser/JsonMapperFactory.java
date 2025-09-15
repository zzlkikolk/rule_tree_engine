package com.github.zzlkikolk.core.parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.github.zzlkikolk.core.model.actions.Actions;
import com.github.zzlkikolk.core.model.rule.RootNode;
import com.github.zzlkikolk.core.parser.actions.ActionDeserializer;
import com.github.zzlkikolk.core.parser.actions.ActionSubtypeLoader;
import com.github.zzlkikolk.core.parser.rule.RootNodeDeserializer;
import com.github.zzlkikolk.core.parser.rule.RootNodeSubtypeLoader;

public class JsonMapperFactory {


    public static ObjectMapper createDynamicMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // 1. 加载所有 SPI 注册的 Actions 子类
        ActionSubtypeLoader.loadAllSubtypes();

        RootNodeSubtypeLoader.loadAllSubtypes();

        // 2. 注册自定义反序列化器
        SimpleModule module = new SimpleModule();
        module.addDeserializer(Actions.class, new ActionDeserializer());
        module.addDeserializer(RootNode.class, new RootNodeDeserializer());
        mapper.registerModule(module);
        return mapper;
    }


//    /**
//     * 将Json解析为节点列表
//     * @param json json规则
//     * @return 规则节点列表
//     */
//    public List<RootNode> parse(String json)  {
//        try {
//            return objectMapper.readValue(json,new TypeReference<List<RootNode>>() {});
//        } catch (Exception e) {
//            throw new JsonFormatException(e.getMessage(), json,e);
//        }
//    }
}

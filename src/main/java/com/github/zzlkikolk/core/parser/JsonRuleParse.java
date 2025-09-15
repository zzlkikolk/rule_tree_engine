package com.github.zzlkikolk.core.parser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.zzlkikolk.core.model.rule.RootNode;
import com.github.zzlkikolk.exception.JsonFormatException;

import java.util.List;

public class JsonRuleParse {
    private static final ObjectMapper objectMapper = JsonMapperFactory.createDynamicMapper();

    public static List<RootNode> parse(String json) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<RootNode>>() {
            });
        } catch (Exception e) {
            throw new JsonFormatException(e.getMessage(), json, e);
        }
    }
}

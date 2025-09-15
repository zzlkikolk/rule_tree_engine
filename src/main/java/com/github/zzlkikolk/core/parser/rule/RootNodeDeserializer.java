package com.github.zzlkikolk.core.parser.rule;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.zzlkikolk.core.model.rule.RootNode;

import java.io.IOException;

public class RootNodeDeserializer extends StdDeserializer<RootNode> {


    public RootNodeDeserializer() {
        super(RootNode.class);
    }

    @Override
    public RootNode deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String type = node.get("type").asText();

        Class<? extends RootNode> actionClass = RootNodeTypeRegistry.getRootNodeClass(type);
        if (actionClass == null) {
            throw new JsonParseException(p, "Unknown rootNode type: " + type +
                    ". Registered types: " + RootNodeTypeRegistry.getAllRegisteredTypes());
        }

        return p.getCodec().treeToValue(node, actionClass);
    }
}

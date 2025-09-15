package com.github.zzlkikolk.core.parser.actions;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.github.zzlkikolk.core.model.actions.Actions;

import java.io.IOException;

public class ActionDeserializer extends StdDeserializer<Actions> {


    public ActionDeserializer() {
        super(Actions.class);
    }

    @Override
    public Actions deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        JsonNode node = p.getCodec().readTree(p);
        String type = node.get("type").asText();

        Class<? extends Actions> actionClass = ActionTypeRegistry.getActionClass(type);
        if (actionClass == null) {
            throw new JsonParseException(p, "Unknown action type: " + type +
                    ". Registered types: " + ActionTypeRegistry.getAllRegisteredTypes());
        }

        return p.getCodec().treeToValue(node, actionClass);
    }
}

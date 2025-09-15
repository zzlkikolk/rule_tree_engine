package com.github.zzlkikolk.core.parser;

import com.github.zzlkikolk.core.model.rule.RootNode;
import com.github.zzlkikolk.core.parser.actions.ActionTypeManager;
import com.github.zzlkikolk.core.parser.rule.RootNodeTypeManager;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonRuleParseTest {

    @Test
    void parse() {
        //指定目录
//        ActionTypeManager.scanPackages("com.github.zzlkikolk.core.model.actions");
//        RootNodeTypeManager.scanPackages("com.github.zzlkikolk.core.model.rule");
        String json = "[{\"type\":\"rapidLargeTrade\",\"value\":{\"amountThreshold\":750,\"timeWindowSeconds\":300}},{\"type\":\"riskScore\",\"value\":{\"score\":750,\"operator\":\">=\"}}]";

        List<RootNode> list = JsonRuleParse.parse(json);

        assertEquals(2, list.size());
    }

    @Test
    void parseLogical() {
        ActionTypeManager.scanPackages("com.github.zzlkikolk.core.model.actions");
        RootNodeTypeManager.scanPackages("com.github.zzlkikolk.core.model.rule");
        String json = "[{\"type\":\"rapidLargeTrade\",\"value\":{\"amountThreshold\":750,\"timeWindowSeconds\":300}},{\"type\":\"logical\",\"operator\":\"AND\",\"children\":[{\"type\":\"riskScore\",\"value\":{\"score\":750,\"operator\":\">=\"}},{\"type\":\"rapidLargeTrade\",\"value\":{\"amountThreshold\":750,\"timeWindowSeconds\":300}}]}]";
        List<RootNode> list = JsonRuleParse.parse(json);
        assertEquals(2, list.size());
    }

    @Test
    void parseRuleAndAction() {
        ActionTypeManager.init();
        RootNodeTypeManager.init();
        String json = "[{\"type\":\"rapidLargeTrade\",\"value\":{\"amountThreshold\":750,\"timeWindowSeconds\":300},\"actions\":[{\"type\":\"blockAccount\"},{\"type\":\"logSuspicious\"}]},{\"type\":\"logical\",\"operator\":\"AND\",\"children\":[{\"type\":\"riskScore\",\"value\":{\"score\":750,\"operator\":\">=\"}},{\"type\":\"rapidLargeTrade\",\"value\":{\"amountThreshold\":750,\"timeWindowSeconds\":300}}],\"actions\":[{\"type\":\"pushNotification\"},{\"type\":\"smsNotification\"}]}]";

        List<RootNode> list = JsonRuleParse.parse(json);
        assertEquals(2, list.size());
        RootNode rootNode = list.get(0);
        assertNotNull(rootNode.getActions());
        assertFalse(rootNode.getActions().isEmpty());
    }
}
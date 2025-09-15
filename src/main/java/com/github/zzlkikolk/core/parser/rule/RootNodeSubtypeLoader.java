package com.github.zzlkikolk.core.parser.rule;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.zzlkikolk.core.model.rule.RootNode;

import java.util.Iterator;
import java.util.ServiceLoader;

public class RootNodeSubtypeLoader {

    public static void loadAllSubtypes() {

        System.out.println("RootNode SPI ClassLoader: " + RootNode.class.getClassLoader());
        ServiceLoader<RootNode> loader = ServiceLoader.load(RootNode.class);

        Iterator<RootNode> iterator = loader.iterator();

        if (!iterator.hasNext()) {
            System.err.println("RootNode ERROR: No RootNode implementations found via SPI!");
        }

        for (RootNode rootNode : loader) {
            Class<? extends RootNode> rootNodeClass = rootNode.getClass();
            JsonTypeName typeAnno = rootNodeClass.getAnnotation(JsonTypeName.class);
            if (typeAnno != null) {
                RootNodeTypeRegistry.register(typeAnno.value(), rootNodeClass);
            }
        }
    }
}

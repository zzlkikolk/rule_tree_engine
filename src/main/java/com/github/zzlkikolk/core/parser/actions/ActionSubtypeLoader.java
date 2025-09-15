package com.github.zzlkikolk.core.parser.actions;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.github.zzlkikolk.core.model.actions.Actions;
import com.github.zzlkikolk.core.model.rule.RootNode;

import java.util.Iterator;
import java.util.ServiceLoader;

public class ActionSubtypeLoader {

    public static void loadAllSubtypes() {
        System.out.println("Actions SPI ClassLoader: " + RootNode.class.getClassLoader());

        ServiceLoader<Actions> loader = ServiceLoader.load(Actions.class);
        Iterator<Actions> iterator = loader.iterator();

        if (!iterator.hasNext()) {
            System.err.println("Actions ERROR: No RootNode implementations found via SPI!");
        }
        for (Actions action : loader) {
            Class<? extends Actions> actionClass = action.getClass();
            JsonTypeName typeAnno = actionClass.getAnnotation(JsonTypeName.class);
            if (typeAnno != null) {
                ActionTypeRegistry.register(typeAnno.value(), actionClass);
            }
        }
    }
}

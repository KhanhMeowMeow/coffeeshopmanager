package com.example.view.event;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class GLOBAL_MENU_CLICK_ACTIVEBUTTON{
    
    public static Map<String, List<Consumer<Object>>> mapEventButton = new ConcurrentHashMap();

    public void listenerEvent(String eventType, Consumer<Object> handler) {
        mapEventButton.computeIfAbsent(eventType, k -> new java.util.ArrayList<>()).add(handler);
    }

    public void fireEvent(String eventType, Object data) {
        List<Consumer<Object>> handlers = mapEventButton.get(eventType);
        if (handlers != null) {
            for (Consumer<Object> handler : handlers) {
                handler.accept(data);
            }
        }
    }

    public void unsubscribe(String eventType, Consumer<Object> handler) {
        List<Consumer<Object>> handlers = mapEventButton.get(eventType);
        if (handlers != null) {
            handlers.remove(handler);
            if (handlers.isEmpty()) {
                mapEventButton.remove(eventType);
            }
        }
    }
    
}

package com.coen92.eventpublishing.staticpublishingclass;

import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@AllArgsConstructor
public class HandlersRegistry {

    private List<EventHandler> handlers;

    public <T extends DomainEvent> List<EventHandler> getHandlers(Class<T> eventClass) {
        return switch (eventClass) {
            // implementation based on class of event
            default -> handlers;
        };
    }
}

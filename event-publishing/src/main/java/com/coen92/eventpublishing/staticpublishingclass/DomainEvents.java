package com.coen92.eventpublishing.staticpublishingclass;

import lombok.RequiredArgsConstructor;

// check: https://udidahan.com/2009/06/14/domain-events-salvation/ for implementation
@RequiredArgsConstructor
public /*static*/ class DomainEvents {
    private static HandlersRegistry handlersRegistry;

    public static void publish(DomainEvent event) {
        handlersRegistry.getHandlers(event.getClass())
                .forEach(handler -> handler.handle(event));
    }
}

package com.coen92.eventpublishing.staticpublishingclass;

public interface EventHandler<T extends DomainEvent> {

    void handle(T event);
}

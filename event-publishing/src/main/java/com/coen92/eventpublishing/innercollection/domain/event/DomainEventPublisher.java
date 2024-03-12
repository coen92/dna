package com.coen92.eventpublishing.innercollection.domain.event;

import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
@FunctionalInterface
public interface DomainEventPublisher {
    // implementation in particular classes of Infrastructure layer
    void publish(DomainEvent event);

    default void publish(Collection<DomainEvent> events) {
        events.forEach(this::publish);
    }
}

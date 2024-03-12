package com.coen92.eventpublishing.innercollection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

abstract class Entity {
    private final List<DomainEvent> events = new ArrayList<>();

    // usable only by Aggregate who extends Entity class, generates/publish Event in scope of Aggregate changes
    protected void raise(DomainEvent event) {
        events.add(event);
    }

    // for all classes that needs to use information about already happened Events and act upon their data
    public Collection<DomainEvent> getChanges() {
        return events;
    }

    public void clearEvents() {
        events.clear();
    }
}

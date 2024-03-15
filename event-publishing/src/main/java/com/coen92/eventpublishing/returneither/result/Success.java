package com.coen92.eventpublishing.returneither.result;

import com.coen92.eventpublishing.returneither.DomainEvent;
import com.coen92.eventpublishing.returneither.EventId;

public abstract class Success<T> implements Result<T>, DomainEvent {
    protected final EventId id;
    protected final T success;
    protected Success(EventId id, T success) {
        this.id = id;
        this.success = success;
    }
}

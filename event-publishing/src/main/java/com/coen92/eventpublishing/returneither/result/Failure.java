package com.coen92.eventpublishing.returneither.result;

import com.coen92.eventpublishing.returneither.DomainEvent;
import com.coen92.eventpublishing.returneither.EventId;
import lombok.Getter;


public abstract class Failure<T> implements Result<T>, DomainEvent {
    @Getter
    protected final EventId id;
    protected final Throwable cause;
    protected T failure;

    protected Failure(EventId id, Throwable cause, T failure) {
        this.id = id;
        this.cause = cause;
        this.failure = failure;
    }
}

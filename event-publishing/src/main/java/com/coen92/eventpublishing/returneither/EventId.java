package com.coen92.eventpublishing.returneither;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode(of = "id")
public class EventId {
    private final UUID id;
    public EventId(UUID id) {
        this.id = id;
    }
}

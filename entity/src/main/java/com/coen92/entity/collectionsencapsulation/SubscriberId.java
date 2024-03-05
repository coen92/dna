package com.coen92.entity.collectionsencapsulation;

import lombok.Getter;

import java.util.UUID;

@Getter
public class SubscriberId {
    private final UUID id;

    public SubscriberId(UUID id) {
        this.id = id;
    }
}

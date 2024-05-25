package com.coen92.dna.pruchase;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
final class PurchaseId {
    final UUID value;

    public PurchaseId(UUID value) {
        this.value = value;
    }
}

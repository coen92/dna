package com.coen92.entity.subscription;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class IndividualSubscription {
    private SubscriptionId id;
    Instant lastDisablement;

    // no getters, no setters -> mutability through methods following business rules
    public void disable(DisableReason disableReason) {
        // disable rules set - CONTEXTUAL INVARIANTS
        if (disableReason.isEmpty()) {
            // do not disable
        }
        if (lastDisablement.isBefore(Instant.now().minus(1, ChronoUnit.DAYS))) {
            // do not disable
        }
        // disable rules set - CONSTANT INVARIANTS
        checkConstantInvariants();
        // state changes - disable
    }

    public void activate() {
        // activate rules set - CONSTANT INVARIANTS
        checkConstantInvariants();
        // state changes - activate
    }

    public void pause() {
        // pause rules set - CONSTANT INVARIANTS
        checkConstantInvariants();
        // state changes - activate
    }

    private void checkConstantInvariants() {
        if (id == null) {
            throw new IllegalStateException("Subscription id must be declared!");
        }
    }



    @NoArgsConstructor
    @AllArgsConstructor
    private static class DisableReason {
        private Reason reason;

        boolean isEmpty() {
            return this.reason == null;
        }

        private enum Reason {
            BLOCKED,
            NO_PAYMENT,
            MALFORMED_USER
        }
    }
}

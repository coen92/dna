package com.coen92.subscriptionmodule.model;

import java.time.Duration;
import java.time.Instant;

class Pauses {
    int availablePauses = 2;
    Instant lastPause = null;

    void markNewPauseAt(Instant when) {
        lastPause = when;
        availablePauses--;
    }

    boolean canPauseAt(Instant when) {
        return anyPauseAvailable() && enoughDaysSinceLastPause(when);
    }

    private boolean anyPauseAvailable() {
        return availablePauses > 0;
    }

    private boolean enoughDaysSinceLastPause(Instant when) {
        if (lastPause == null) {
            return true;
        }
        return Duration.between(lastPause, when).toDays() >= 10;
    }
}

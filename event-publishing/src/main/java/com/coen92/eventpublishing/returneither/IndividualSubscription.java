package com.coen92.eventpublishing.returneither;


import com.coen92.eventpublishing.returneither.result.PauseFailed;
import com.coen92.eventpublishing.returneither.result.SubscriptionPaused;
import io.vavr.control.Either;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IndividualSubscription {
    @Getter
    private final boolean disabled;
    @Getter
    private final SubscriptionId id;
    Status status;

    enum Status {New, Activated, Deactivated, Paused, PastDue}

    public Either<PauseFailed, SubscriptionPaused> pauseResult() {
        try {
            pause();
            return Either.right(new SubscriptionPaused(id));
        } catch (Exception e) {
            return Either.left(new PauseFailed(e, id));
        }
    }

    private void pause() {
        if (isDisabled()) {
            throw new IllegalStateException(STR."Subscritpion subId: \{this.id} is disabled!");
        }
        status = Status.Paused;
    }
}

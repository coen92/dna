package com.coen92.domainservice.subscription.pause;

import com.coen92.domainservice.subscription.IndividualSubscription;
import com.coen92.domainservice.subscription.Result;
import com.coen92.domainservice.subscription.Subscription;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PauseResult implements Result {
    private final IndividualSubscription subscription;

    PauseResult(IndividualSubscription subscription) {
        this.subscription = subscription;
    }

    public static Result execute(Subscription subscription) {
        var individual = (IndividualSubscription) subscription;
        return new PauseResult(individual);
    }
}

package com.coen92.domainservice.subscription.pause;

import com.coen92.domainservice.subscription.IndividualSubscription;
import com.coen92.domainservice.subscription.Result;
import com.coen92.domainservice.subscription.Subscriber;
import com.coen92.domainservice.subscription.policy.PremiumPausingPolicy;
import com.coen92.domainservice.subscription.policy.StandardPausingPolicy;

// domain service example
// kept in domain layer for Subscription domain
// can be injected and used in application layer that orchestrates domain processes
class PauseSubscriptionDomainService {

    Result pause(Subscriber subscriber, IndividualSubscription subscription) {
        Subscriber.LoyaltyPoints points = subscriber.currentPoints();

        if (points.qualifyToPremium()) {
            return subscription.pause(new PremiumPausingPolicy());
        }
        return subscription.pause(new StandardPausingPolicy());
    }
}

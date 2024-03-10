package com.coen92.domainservice.subscription.pause

import com.coen92.domainservice.subscription.IndividualSubscription
import com.coen92.domainservice.subscription.Subscriber
import com.coen92.domainservice.subscription.SubscriberId
import spock.lang.Specification
import spock.lang.Subject

import static com.coen92.domainservice.subscription.Subscriber.LoyaltyPoint
import static com.coen92.domainservice.subscription.Subscriber.LoyaltyPoints

class PauseSubscriptionDomainServiceSpec extends Specification {
    private static final Integer PREMIUM_SUBSCRIPTION_POINTS_THRESHOLD =  5

    @Subject
    PauseSubscriptionDomainService service = new PauseSubscriptionDomainService()

    def 'premium subscriber can always pause subscription'() {
        given:
            def individualSub = IndividualSubscription.newSub()
        and:
            def subscriber = new Subscriber(SubscriberId.of(UUID.randomUUID()), enoughToBePremium())
        when:
            def result = service.pause(subscriber, individualSub)
        then:
            result == new PauseResult(individualSub)
    }

    LoyaltyPoints enoughToBePremium() {
        var points = new ArrayList<LoyaltyPoints>()

        for (int i = 0; i <= PREMIUM_SUBSCRIPTION_POINTS_THRESHOLD; i ++) {
            var point = LoyaltyPoint.gainOne()
            points.add(point)
        }

        return new LoyaltyPoints(points)
    }
}

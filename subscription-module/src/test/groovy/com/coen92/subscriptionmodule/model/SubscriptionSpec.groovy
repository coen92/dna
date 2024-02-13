package com.coen92.subscriptionmodule.model

import spock.lang.Specification
import spock.lang.Subject

import java.time.Duration
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

class SubscriptionSpec extends Specification {
    Instant someday = LocalDate.of(1989, 12, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()

    @Subject
    Subscription sub = new Subscription()


    def 'should activate new sub'() {
        expect:
            sub.activate().isSuccessful()
    }

    def 'should deactivate active sub'() {

    }

    def 'should pause activated sub'() {
        given:
            sub.activate()
        expect:
            sub.pause(null).isSuccessful()
    }

    def 'should not pause not active sub'() {
        expect:
            sub.pause(null).isFailure()
    }

    def 'should not pause when all pauses are used'() {
        given:
            sub.activate()
        and:
            assert sub.pause(null, someday.plus(Duration.ofDays(10))).isSuccessful()
            assert sub.resume().isSuccessful()
        and:
            assert sub.pause(null, someday.plus(Duration.ofDays(20))).isSuccessful()
            assert sub.resume().isSuccessful()
        expect:
            sub.pause(null, someday.plus(Duration.ofDays(100))).isFailure()
    }

    def 'should not pause if less than 10 days from last pause'() {
        given:
            sub.activate()
        and:
            assert sub.pause(null, someday.plus(Duration.ofDays(10))).isSuccessful()
            assert sub.resume().isSuccessful()
        expect:
            sub.pause(null, someday.plus(Duration.ofDays(13))).isFailure()
    }

    def 'should resume sub'() {
        given:
            sub.activate()
        and:
            sub.pause(null)
        expect:
            sub.resume().isSuccessful()
    }

    def 'should mark as past due'() {
        given:
            sub.activate()
        expect:
            sub.markAsPastDue().isSuccessful()
    }
}

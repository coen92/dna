package com.coen92.entity.collectionsencapsulation;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


public class CompanySubscription {
    private final WaitingList waitingList;
    private final Subscribers subscribers;

    CompanySubscription(WaitingList waitingList, Subscribers subscribers) {
        this.waitingList = waitingList;
        this.subscribers = subscribers;
    }

    public Enrollment enroll(SubscriberId subscriberId) {
        if (subscribers.alreadyContain(subscriberId)) {
            return Enrollment.enrolled();
        }
        if (subscribers.withinLimits()) {
            subscribers.enroll(subscriberId);
            return Enrollment.enrolled();
        }
        waitingList.enroll(subscriberId);
        return Enrollment.onWaitingList();
    }


    // Encapsulating default enabled methods from List class
    // exposing only these methods we want to use in Entity processes
    @Setter
    @Getter
    private static class WaitingList {
        List<SubscriberId> waitingList;

        public void enroll(SubscriberId subscriberId) {
            waitingList.add(subscriberId);
        }
    }

    private static class Subscribers {
        List<SubscriberId> subscribers;

        private static final Integer MAX_LIMIT_OF_SUBS = 20;

        public boolean alreadyContain(SubscriberId subscriberId) {
            return subscribers.contains(subscriberId);
        }

        public boolean withinLimits() {
            return subscribers.size() <= MAX_LIMIT_OF_SUBS;
        }

        public void enroll(SubscriberId subscriberId) {
            subscribers.add(subscriberId);
        }
    }
}

package com.coen92.domainservice.subscription;

import lombok.AllArgsConstructor;

import java.util.List;
import java.util.UUID;

public record Subscriber(
        SubscriberId id,
        LoyaltyPoints loyaltyPoints
) {

    public LoyaltyPoints currentPoints() {
        return this.loyaltyPoints;
    }

    @AllArgsConstructor
    public static class LoyaltyPoints {
        private static final Integer PREMIUM_SUBSCRIPTION_POINTS_THRESHOLD =  5;
        List<LoyaltyPoint> points;

        LoyaltyPoints gainPoint() {
            points.add(LoyaltyPoint.gainOne());
            return new LoyaltyPoints(points);
        }

        public boolean qualifyToPremium() {
            return this.points.size() > PREMIUM_SUBSCRIPTION_POINTS_THRESHOLD;
        }

    }

    record LoyaltyPoint(UUID id) {
        static LoyaltyPoint gainOne() {
            var id = UUID.randomUUID();
            return new LoyaltyPoint(id);
        }
    }
}

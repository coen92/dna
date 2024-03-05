package com.coen92.entity.policy;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

class VacationPausingPolicy implements PausingPolicy {
    @Override
    public Instant pauseEnd() {
        var endOfAugust = LocalDate.of(LocalDate.now().getYear(), Month.AUGUST, 31);
        return endOfAugust.atStartOfDay(ZoneId.systemDefault()).toInstant();
    }
}

package com.coen92.valueobject.course;

import java.util.List;

public class Course {
    CourseCapacity capacity;
    List<Participant> participants;

    void enrollNewSubscriber() {
        // enrolling new participant is checked against allowed BEHAVIOUR
        if (capacity.canAccept(participants.size() + 1)) {
            // todo: implementation
        }
    }

    boolean isOnSite() {
        // todo: implementation
        return true;
    }
}

package com.coen92.entity.collectionsencapsulation;

public record Enrollment(Status status) {

    static Enrollment enrolled() {
        return new Enrollment(Status.Enrolled);
    }

    public static Enrollment onWaitingList() {
        return new Enrollment(Status.Scheduled);
    }

    enum Status {
        Enrolled,
        Scheduled,
        Open
    }
}

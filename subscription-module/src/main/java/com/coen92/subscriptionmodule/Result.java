package com.coen92.subscriptionmodule;

import com.coen92.subscriptionmodule.model.DomainEvent;

public record Result(Status status) {
    public static Result success(DomainEvent event) {
        return Result.ofStatus(Status.OK);
    }

    public static Result failure(DomainEvent event) {
        return Result.ofStatus(Status.FAILURE);
    }

    private static Result ofStatus(Status status) {
        return new Result(status);
    }

    public boolean isSuccessful() {
        return this.status == Status.OK;
    }

    public boolean isFailure() {
        return !this.isSuccessful();
    }

    private enum Status {
        OK,
        FAILURE
    }
}

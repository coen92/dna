package com.coen92.eventpublishing.innercollection.application;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Result {
    private final Status status;

    enum Status { Succeed, Failed }

    static Result success() {
        return new Result(Status.Succeed);
    };

    static Result failure() {
        return new Result(Status.Failed);
    };
}

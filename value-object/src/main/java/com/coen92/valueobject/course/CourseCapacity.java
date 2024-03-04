package com.coen92.valueobject.course;

import static java.lang.StringTemplate.STR;

public final class CourseCapacity {
    private static final int ON_SITE_COURSE_CAPACITY_LIMIT = 20;
    private final int capacity;

    // private constructor
    private CourseCapacity(int capacity) {
        this.capacity = capacity;
    }

    // VALIDATION of business rules during creation
    public static CourseCapacity of(int capacity) {
        if (capacity < 0) {
            throw new IllegalStateException("Capacity cannot be a negative number");
        }
        return new CourseCapacity(capacity);
    }

    public static CourseCapacity of(int capacity, Course course) {
        if (assertOnSiteCourseLimit(capacity, course)) {
            throw new IllegalStateException(STR."On site courses can't have more than \{ON_SITE_COURSE_CAPACITY_LIMIT} participants!");
        }
        return of(capacity);
    }

    // BEHAVIOUR to be checked on aggregate level
    boolean canAccept(int expected) {
        return this.capacity >= expected;
    }

    // COMPOSITION method for CourseCapacity
    CourseCapacity add(CourseCapacity courseCapacity) {
        return new CourseCapacity(capacity + courseCapacity.capacity);
    }

    private static boolean assertOnSiteCourseLimit(int capacity, Course course) {
        return course.isOnSite() && capacity > ON_SITE_COURSE_CAPACITY_LIMIT;
    }

    CourseCapacity withExtraSpace(CourseCapacity extraSpace) {
        return new CourseCapacity(this.capacity + extraSpace.capacity);
    }
}

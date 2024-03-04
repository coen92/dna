package com.coen92.valueobject.course;

public class CourseCapacity {
    private final int capacity;

    public CourseCapacity(int capacity) {
        this.capacity = capacity;
    }

    CourseCapacity withExtraSpace(CourseCapacity extraSpace) {
        return new CourseCapacity(this.capacity + extraSpace.capacity);
    }
}

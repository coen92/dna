package com.coen92.entity.specification;

public interface Specification<T> {
    boolean isSatisfiedBy(T t);

    /* SPECIFICATION COMPOSITIONS methods

    Specification<T> and(Specification<T> specification);

    Specification<T> or(Specification<T> specification);

    Specification<T> not(Specification<T> specification);
    */
}

package com.coen92.dna.pruchase;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
final class Product {
    final String name;

    public Product(String name) {
        this.name = name;
    }
}

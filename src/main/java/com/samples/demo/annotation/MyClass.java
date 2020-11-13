package com.samples.demo.annotation;

public class MyClass {
    private Integer rank;

    public void setRank(@MyAnnotation(name = "Garage", level = 1) Integer rank) {
        this.rank = rank;
    }
}

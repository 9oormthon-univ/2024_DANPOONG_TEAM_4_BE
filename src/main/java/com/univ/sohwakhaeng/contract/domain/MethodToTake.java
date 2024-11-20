package com.univ.sohwakhaeng.contract.domain;

public enum MethodToTake {
    DELIVERY("delivery"), PICKUP("pickup");

    MethodToTake(String methodToTake) {
    }

    public static MethodToTake of(String methodToTake) {
        if (methodToTake.equals("delivery")) {
            return DELIVERY;
        } else if (methodToTake.equals("pickup")) {
            return PICKUP;
        } else {
            throw new IllegalArgumentException("Method to take must be either delivery or pickup");
        }
    }
}

package com.univ.sohwakhaeng.enterprise;

public enum Category {
    AGRICULTURAL_PRODUCTS("AGRICULTURAL_PRODUCTS"),
    SEAFOOD("SEAFOOD"),
    MEAT("MEAT"),
    BAKERY("BAKERY"),
    DAIRY_AND_EGGS("DAIRY_AND_EGGS"),
    FLORICULTURE("FLORICULTURE");

    private final String description;

    Category(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}
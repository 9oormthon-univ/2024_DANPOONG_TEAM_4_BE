package com.univ.sohwakhaeng.enterprise;

public enum Category {
    AGRICULTURAL_PRODUCTS("농산물"),
    SEAFOOD("해산물"),
    MEAT("육류"),
    BAKERY("베이커리"),
    DAIRY_AND_EGGS("유제품 및 계란"),
    FLORICULTURE("화훼");

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
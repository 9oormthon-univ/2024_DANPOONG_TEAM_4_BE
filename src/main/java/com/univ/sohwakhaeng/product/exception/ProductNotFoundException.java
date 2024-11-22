package com.univ.sohwakhaeng.product.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) {
        super(msg);
    }
}

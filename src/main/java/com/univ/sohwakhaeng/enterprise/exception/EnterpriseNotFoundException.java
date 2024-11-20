package com.univ.sohwakhaeng.enterprise.exception;

import lombok.Getter;

@Getter
public class EnterpriseNotFoundException extends Exception {
    public EnterpriseNotFoundException(String msg) {
        super(msg);
    }
}

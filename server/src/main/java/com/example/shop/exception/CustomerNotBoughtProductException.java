package com.example.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CustomerNotBoughtProductException extends RuntimeException {
    public CustomerNotBoughtProductException(long clientId, long productId) {
        super(String.format("customer[%d] don't bought this product[%d]", clientId, productId));
    }
}

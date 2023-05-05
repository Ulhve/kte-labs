package com.example.shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class IncorrectFinalPriceException extends RuntimeException {
    public IncorrectFinalPriceException(long sum) {
        super(String.format("incorrect final price %d", sum));
    }
}

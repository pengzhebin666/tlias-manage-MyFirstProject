package com.itheima.exception;

import org.springframework.web.bind.annotation.RestControllerAdvice;


public class PeopleException  extends  Exception{
    public PeopleException(String message) {
        super(message);
    }
}

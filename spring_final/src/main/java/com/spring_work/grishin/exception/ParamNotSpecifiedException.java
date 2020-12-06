package com.spring_work.grishin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ParamNotSpecifiedException extends RuntimeException {
    public ParamNotSpecifiedException(){
        super();
    }

    public ParamNotSpecifiedException(String mess){
        super(mess);
    }

    public ParamNotSpecifiedException(String mess, Throwable cause){
        super(mess, cause);
    }
}

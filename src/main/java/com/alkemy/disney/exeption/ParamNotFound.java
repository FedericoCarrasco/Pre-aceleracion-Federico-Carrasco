package com.alkemy.disney.exeption;

public class ParamNotFound extends RuntimeException {
    public ParamNotFound(String error) { super(error); }
}

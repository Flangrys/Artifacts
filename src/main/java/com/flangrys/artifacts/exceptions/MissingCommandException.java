package com.flangrys.artifacts.exceptions;

public class MissingCommandException extends ClassNotFoundException {
    public MissingCommandException(String s) {
        super(s);
    }
}

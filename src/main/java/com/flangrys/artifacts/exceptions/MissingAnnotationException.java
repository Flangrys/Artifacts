package com.flangrys.artifacts.exceptions;

public class MissingAnnotationException extends ClassNotFoundException {
    public MissingAnnotationException(String s) {
        super(s);
    }
}

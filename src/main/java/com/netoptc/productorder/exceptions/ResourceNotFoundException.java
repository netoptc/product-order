package com.netoptc.productorder.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() { super("Recuso não entrado"); }
}

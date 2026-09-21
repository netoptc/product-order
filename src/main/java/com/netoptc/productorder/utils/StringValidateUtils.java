package com.netoptc.productorder.utils;

public class StringValidateUtils {
    public static Boolean isAlphaWithSpaces(String text) {
        return text.matches("^[a-zA-ZÀ-ÿ ]*$");
    }
}

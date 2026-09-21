package com.netoptc.productorder.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringValidateUnitTests {

    @Test
    public void isAlphaWithSpacesShouldReturnTrueWhenValidString() {
        Boolean result =  StringValidateUtils.isAlphaWithSpaces("String test");
        Assertions.assertTrue(result);
    }

    @Test
    public void isAlphaWithSpacesShouldReturnFalseWhenInvalidString() {
        Boolean result =  StringValidateUtils.isAlphaWithSpaces("String test @!$");
        Assertions.assertFalse(result);
    }

}

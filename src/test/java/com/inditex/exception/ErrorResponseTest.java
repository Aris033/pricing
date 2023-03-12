package com.inditex.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
class ErrorResponseTest {

    @Test
    void testConstructorAndGetMessage() {
        String message = "Error message";
        ErrorResponse errorResponse = new ErrorResponse(message);

        Assertions.assertEquals(message, errorResponse.getMessage());
    }

    @Test
    void testSetAndGetMessage() {
        String message = "Error message";
        ErrorResponse errorResponse = new ErrorResponse("");
        errorResponse.setMessage(message);

        Assertions.assertEquals(message, errorResponse.getMessage());
    }
}


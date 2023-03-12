package com.inditex.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotFoundExceptionTest {

    @Test
    void testConstructorWithMessage() {
        String message = "Not found";
        NotFoundException exception = new NotFoundException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }

    @Test
    void testConstructorWithMessageAndCause() {
        String message = "Not found";
        Throwable cause = new Throwable("Cause");
        NotFoundException exception = new NotFoundException(message, cause);

        Assertions.assertEquals(message, exception.getMessage());
        Assertions.assertEquals(cause, exception.getCause());
    }

}
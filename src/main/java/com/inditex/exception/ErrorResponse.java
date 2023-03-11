package com.inditex.exception;

public class ErrorResponse {
    private String message;

    /**
     * Constructs a new ErrorResponse with the specified message.
     *
     * @param message the message to include in the ErrorResponse
     */
    public ErrorResponse(String message) {
        this.message = message;
    }

    /**
     * Returns the message included in this ErrorResponse.
     *
     * @return the message included in this ErrorResponse
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message included in this ErrorResponse.
     *
     * @param message the message to include in this ErrorResponse
     */
    public void setMessage(String message) {
        this.message = message;
    }
}

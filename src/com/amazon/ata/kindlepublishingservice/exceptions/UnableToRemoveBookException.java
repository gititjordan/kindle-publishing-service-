package com.amazon.ata.kindlepublishingservice.exceptions;

public class UnableToRemoveBookException extends RuntimeException{

    private static final long serialVersionUID = -3635741317645366272L;

    /**
     * Exception with a message, but no cause.
     * @param message A descriptive message for this exception.
     */
    public UnableToRemoveBookException(String message) {
        super(message);
    }

    /**
     * Exception with message and cause.
     * @param message A descriptive message for this exception.
     * @param cause The original throwable resulting in this exception.
     */
    public UnableToRemoveBookException(String message, Throwable cause) {
        super(message, cause);
    }
}

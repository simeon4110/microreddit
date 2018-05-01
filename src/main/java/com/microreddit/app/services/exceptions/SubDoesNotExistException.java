package com.microreddit.app.services.exceptions;

/**
 * @author Josh Harkema
 */
public class SubDoesNotExistException extends RuntimeException {
    public SubDoesNotExistException() {
        super();
    }

    public SubDoesNotExistException(String message) {
        super(message);
    }

    public SubDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubDoesNotExistException(Throwable cause) {
        super(cause);
    }
}

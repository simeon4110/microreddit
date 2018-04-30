package com.microreddit.app.services.exceptions;

/**
 * @author Josh Harkema
 */
public class SubAlreadyExistsException extends RuntimeException {
    public SubAlreadyExistsException() {
        super();
    }

    public SubAlreadyExistsException(String message) {
        super(message);
    }

    public SubAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public SubAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}

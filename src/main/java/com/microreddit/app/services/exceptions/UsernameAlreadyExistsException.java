package com.microreddit.app.services.exceptions;

/**
 * @author Josh Harkema
 */
public final class UsernameAlreadyExistsException extends RuntimeException {
    public UsernameAlreadyExistsException() {
        super();
    }

    public UsernameAlreadyExistsException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UsernameAlreadyExistsException(final String message) {
        super(message);
    }

    public UsernameAlreadyExistsException(final Throwable cause) {
        super(cause);
    }
}

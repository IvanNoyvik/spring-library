package by.gomel.noyvik.library.exception;

public class LockedLoginException extends ServiceException {

    public LockedLoginException() {
    }

    public LockedLoginException(String message) {
        super(message);
    }

    public LockedLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public LockedLoginException(Throwable cause) {
        super(cause);
    }
}

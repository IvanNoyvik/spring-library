package by.gomel.noyvik.library.exception;

public class DaoPartException extends RuntimeException {

    public DaoPartException() {
    }

    public DaoPartException(String message) {
        super(message);
    }

    public DaoPartException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoPartException(Throwable cause) {
        super(cause);
    }
}

package exception;

public class PersistenciaException extends Exception {
    public PersistenciaException() {
        super();
    }

    public PersistenciaException(String message) {
        super(message);
    }

    public PersistenciaException(String message, Throwable cause) {
        super(message, cause);
    }
}

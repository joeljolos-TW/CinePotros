package excepcion;

public class NegocioException extends Exception {
    public NegocioException() {
        super();
    }

    public NegocioException(String message) {
        super(message);
    }

    public NegocioException(String message, Throwable cause) {
        super(message, cause);
    }
}

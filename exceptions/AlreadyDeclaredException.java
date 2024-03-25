package exceptions;

public class AlreadyDeclaredException extends RuntimeException {

    public AlreadyDeclaredException(String message) {
        super(message);
    }
}

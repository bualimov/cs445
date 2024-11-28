public class AlreadyExistsException extends Exception {
    // message
    public AlreadyExistsException() {
        super("An item already exists in the system.");
    }

    // custom message
    public AlreadyExistsException(String message) {
        super(message);
    }
}

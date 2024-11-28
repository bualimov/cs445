public class NonexistentException extends Exception {
    // constructor
    public NonexistentException() {
        super("The requested item does not exist.");
    }

    // custom message
    public NonexistentException(String message) {
        super(message);
    }
}

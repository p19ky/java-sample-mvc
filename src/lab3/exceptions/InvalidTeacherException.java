package lab3.exceptions;

public class InvalidTeacherException extends Exception {
    public InvalidTeacherException() {
    }

    public InvalidTeacherException(String message) {
        super(message);
    }
}

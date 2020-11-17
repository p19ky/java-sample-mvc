package lab3.exceptions;

public class InvalidCourseException extends Exception{
    public InvalidCourseException() {
    }

    public InvalidCourseException(String message) {
        super(message);
    }
}

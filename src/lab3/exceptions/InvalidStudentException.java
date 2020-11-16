package lab3.exceptions;

public class InvalidStudentException extends Exception{
    public InvalidStudentException() {
    }

    public InvalidStudentException(String message) {
        super(message);
    }
}

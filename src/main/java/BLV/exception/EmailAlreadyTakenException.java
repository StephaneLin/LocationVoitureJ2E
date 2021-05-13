package BLV.exception;

public class EmailAlreadyTakenException extends Exception {
    public EmailAlreadyTakenException() {
        System.err.println("Email is already registered");
    }
}

package BLV.exception;

public class UserEmailCannotBeNullException extends Exception {
    public UserEmailCannotBeNullException() {
        System.err.println("User email cannot be null");
    }
}

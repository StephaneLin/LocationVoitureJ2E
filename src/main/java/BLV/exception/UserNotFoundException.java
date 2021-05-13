package BLV.exception;

public class UserNotFoundException extends Throwable {
    public UserNotFoundException(String email) {
        System.err.println("User with email=" + email + " cannot be found");
    }
}

package BLV.exception;

public class UserIsNotSignedUpException extends Exception {
    public UserIsNotSignedUpException(String email) {
        System.err.println("Trying to connect with email " + email + ", but is not registered");
    }
}

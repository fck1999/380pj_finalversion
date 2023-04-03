package hkmu.comps380f.exception;

public class UserNotFound extends Exception {
    public UserNotFound(String name) {
        super("Username: " + name + " does not exist.");
    }
}

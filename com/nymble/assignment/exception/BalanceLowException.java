package nymble.assignment.exception;

public class BalanceLowException extends RuntimeException{
    public BalanceLowException(String message) {
        super(message);
    }
}

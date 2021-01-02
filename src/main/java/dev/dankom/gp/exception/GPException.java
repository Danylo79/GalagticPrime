package dev.dankom.gp.exception;

public class GPException extends Exception {
    public GPException(String reason, Class<?> throwable) {
        super(reason + " on " + throwable.getSimpleName());
    }
}

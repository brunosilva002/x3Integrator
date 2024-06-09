package br.brn.x3Integrator.exception;

public class BussineRuleException extends RuntimeException{
    public BussineRuleException() {
    }

    public BussineRuleException(String message) {
        super(message);
    }

    public BussineRuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public BussineRuleException(Throwable cause) {
        super(cause);
    }

    public BussineRuleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}


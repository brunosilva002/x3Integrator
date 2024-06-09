package br.brn.x3Integrator.exception;

public class X3IntegrationException extends RuntimeException{
    public X3IntegrationException() {
    }

    public X3IntegrationException(String message) {
        super(message);
    }

    public X3IntegrationException(String message, Throwable cause) {
        super(message, cause);
    }

    public X3IntegrationException(Throwable cause) {
        super(cause);
    }

    public X3IntegrationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }


}


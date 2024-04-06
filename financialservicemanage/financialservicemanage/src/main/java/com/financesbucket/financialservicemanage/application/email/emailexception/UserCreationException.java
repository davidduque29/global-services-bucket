package com.financesbucket.financialservicemanage.application.email.emailexception;

public class UserCreationException extends Exception {

    public UserCreationException(String message) {
        super(message);
    }

    public UserCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}

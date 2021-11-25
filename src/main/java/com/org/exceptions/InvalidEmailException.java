package com.org.exceptions;

import org.openqa.selenium.ElementClickInterceptedException;

public class InvalidEmailException extends RuntimeException {

    public InvalidEmailException(String email) {
        super("Invalid email - "+email+" missing '@'");
    }
}

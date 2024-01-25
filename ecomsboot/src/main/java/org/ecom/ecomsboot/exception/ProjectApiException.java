package org.ecom.ecomsboot.exception;

import org.springframework.http.HttpStatus;

public class ProjectApiException extends Throwable {
    private HttpStatus status;
    private String message;
}

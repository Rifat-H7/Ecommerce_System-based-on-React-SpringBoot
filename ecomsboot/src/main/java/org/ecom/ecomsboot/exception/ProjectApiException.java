package org.ecom.ecomsboot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ProjectApiException  extends RuntimeException{

    private HttpStatus status;
    private String message;

}

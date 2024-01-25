package org.ecom.ecomsboot.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorDetails {

    private LocalDateTime timeStamp;
    private String message;
    private String details;

}
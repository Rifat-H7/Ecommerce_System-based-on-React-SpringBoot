package org.ecom.ecomsboot.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Mail {

    private String to;
    private String code;

}

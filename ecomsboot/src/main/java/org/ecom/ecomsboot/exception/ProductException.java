package org.ecom.ecomsboot.exception;

import lombok.NoArgsConstructor;
import org.ecom.ecomsboot.model.Product;

public class ProductException extends Exception {
    public ProductException(String message) {
        super(message);
    }

}

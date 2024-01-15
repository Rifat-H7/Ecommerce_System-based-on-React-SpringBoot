package org.ecom.ecomsboot.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/*@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor*/
public class PaymentInformation {
    private String cardNumber;
    private String cardHolderName;
    private String expiryDate;
    private String cvv;
}

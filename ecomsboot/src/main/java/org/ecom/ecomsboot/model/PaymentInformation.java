package org.ecom.ecomsboot.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/*@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor*/
public class PaymentInformation {
    private String cardNumber;
    private String cardHolderName;
    private LocalDate expiryDate;
    private String cvv;
}

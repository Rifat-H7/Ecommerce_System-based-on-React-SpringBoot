package org.ecom.ecomsboot.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingRequest {
    private Long productId;
    private double rating;

}

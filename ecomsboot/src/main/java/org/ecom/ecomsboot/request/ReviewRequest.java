package org.ecom.ecomsboot.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewRequest {
    private Long productId;
    private String review;
}

package org.ecom.ecomsboot.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddItemRequest {
    private Long productId;
    private String size;
    private int quantity;
    private Integer price;
}

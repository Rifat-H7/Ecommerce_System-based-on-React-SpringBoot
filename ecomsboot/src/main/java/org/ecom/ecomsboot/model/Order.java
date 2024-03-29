package org.ecom.ecomsboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String orderId;
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems=new ArrayList<>();
    private LocalDateTime orderedOn;
    private LocalDateTime deliveredOn;
    @OneToOne
    private Address shippingAddress;
    //@Embedded
    //private PaymentDetails paymentDetails=new PaymentDetails();
    private double totalPrice;
    private Integer totalDiscountedPrice;
    private Integer discount;
    private String oderStatus;
    private int totalItem;
    private LocalDateTime createdOn;

}

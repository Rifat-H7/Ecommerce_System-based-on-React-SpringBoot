package org.ecom.ecomsboot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String description;
    private Integer price;
    private Integer discountedPrice;
    private double discountedPercentage;
    private String brand;
    private int quantity;

    private String color;
    @Embedded
    @ElementCollection
    private Set<Size> sizes=new HashSet<>();
    private String imageUrl;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating>ratings=new ArrayList<>();
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review>reviews=new ArrayList<>();
    private int numRatings;
    @ManyToOne()
    @JoinColumn(name = "catagory_id")
    private Category category;
    private LocalDateTime createdOn;
}

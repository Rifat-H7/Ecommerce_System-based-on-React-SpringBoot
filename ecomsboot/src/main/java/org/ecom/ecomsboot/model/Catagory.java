package org.ecom.ecomsboot.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Catagory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(max = 50)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_catagory_id")
    private Catagory parentCatagory;
    private int level;
}

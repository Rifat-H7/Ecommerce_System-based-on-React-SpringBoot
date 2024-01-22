package org.ecom.ecomsboot.repository;

import org.ecom.ecomsboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p " +
            "WHERE (p.category.name = :category OR :category = '') " +
            "AND ((:minPrice IS NULL AND :maxPrice IS NULL) OR (p.discountedPrice BETWEEN :minPrice and :maxPrice)) " +
            "AND (:minDiscount IS NULL OR p.discountedPercentage >= :minDiscount) " +
            "ORDER BY " +
            "CASE WHEN :sort = 'price_low' THEN p.discountedPrice END ASC, " +
            "CASE WHEN :sort = 'price_high' THEN p.discountedPrice END DESC "
    )


    public List<Product>filterProducts(@Param("category")String category,
                                       @Param("maxPrice")Integer maxPrice,
                                       @Param("minPrice")Integer minPrice,
                                       @Param("minDiscount")Integer minDiscount,
                                       @Param("sort")String sort);
}

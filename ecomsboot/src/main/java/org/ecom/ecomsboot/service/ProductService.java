package org.ecom.ecomsboot.service;

import org.ecom.ecomsboot.exception.ProductException;
import org.springframework.data.domain.Page;
import org.ecom.ecomsboot.model.Product;
import org.ecom.ecomsboot.request.CreateProductRequest;

import java.util.List;

public interface ProductService {

    Product createProduct(CreateProductRequest req);

    String deleteProduct(Long productId) throws ProductException;

    Product updateProduct(Long productId, Product req) throws ProductException;

    Product findProductById(Long productId) throws ProductException;

    List<Product> findProductByCategory(String category);

    Page<Product> getAllProducts(String category, List<String> colors, List<String> sizes,
                                Integer minPrice,
                                Integer maxPrice,
                                Integer minDiscount,
                                String sort, String stock, Integer pageNumber, Integer pageSize);


}

package org.ecom.ecomsboot.service;

import org.ecom.ecomsboot.exception.ProductException;
import org.springframework.data.domain.Page;
import org.ecom.ecomsboot.model.Product;
import org.ecom.ecomsboot.request.CreateProductRequest;

import java.util.List;

public interface ProductService {
    public Product createProduct(CreateProductRequest req);
    public String deleteProduct(Long productId) throws ProductException;
    public Product updateProduct(Long productId, Product req) throws ProductException;
    public Product getProduct(Long productId) throws ProductException;
    public List<Product> getProductByCategory(String category);
    public Page<Product>getAllProducts(String category, List<String>colors, List<String>sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

    Product findProductById(Long productId);

    List<Product> findAllProducts();
}

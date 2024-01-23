package org.ecom.ecomsboot.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.ecom.ecomsboot.exception.ProductException;
import org.ecom.ecomsboot.model.Product;
import org.ecom.ecomsboot.model.Review;
import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.repository.ProductRepository;
import org.ecom.ecomsboot.repository.ReviewRepository;
import org.ecom.ecomsboot.request.ReviewRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService{
    private ReviewRepository reviewRepository;
    private ProductService productService;
    private ProductRepository productRepository;
    @Override
    public Review createReview(ReviewRequest req, User user) throws ProductException {
        Product product=productService.findProductById(req.getProductId());
        Review review=new Review();
        review.setProduct(product);
        review.setUser(user);
        review.setReview(req.getReview());
        review.setCreatedOn(LocalDateTime.now());
        return reviewRepository.save(review);
    }

    @Override
    public List<Review> getProductsReview(Long productId) {
        return reviewRepository.getAllProductsReview(productId);
    }
}

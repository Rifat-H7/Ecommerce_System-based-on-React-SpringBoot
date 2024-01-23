package org.ecom.ecomsboot.service;

import org.ecom.ecomsboot.exception.ProductException;
import org.ecom.ecomsboot.model.Review;
import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.request.ReviewRequest;

import java.util.List;

public interface ReviewService {
    public Review createReview(ReviewRequest req, User user) throws ProductException;
    public List<Review> getProductsReview(Long productId);
}

package org.ecom.ecomsboot.service;

import org.ecom.ecomsboot.exception.ProductException;
import org.ecom.ecomsboot.model.Rating;
import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.request.RatingRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    public Rating createRating(RatingRequest req, User user) throws ProductException;
    public List<Rating> getProductsRating(Long productId);


}

package org.ecom.ecomsboot.service;

import lombok.AllArgsConstructor;
import org.ecom.ecomsboot.exception.ProductException;
import org.ecom.ecomsboot.model.Product;
import org.ecom.ecomsboot.model.Rating;
import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.repository.RatingRepository;
import org.ecom.ecomsboot.request.RatingRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RatingServiceImpl implements RatingService{
    private RatingRepository ratingRepository;
    private ProductService productService;
    @Override
    public Rating createRating(RatingRequest req, User user) throws ProductException {
        Product product=productService.findProductById(req.getProductId());

        Rating rating=new Rating();
        rating.setProduct(product);
        rating.setUser(user);
        rating.setRating(req.getRating());
        rating.setCreatedOn(LocalDateTime.now());
        return ratingRepository.save(rating);
    }

    @Override
    public List<Rating> getProductsRating(Long productId) {
        return ratingRepository.getAllProductsRating(productId);
    }
}

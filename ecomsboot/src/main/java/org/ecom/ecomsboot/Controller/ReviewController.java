package org.ecom.ecomsboot.Controller;

import org.ecom.ecomsboot.exception.ProductException;
import org.ecom.ecomsboot.exception.UserException;
import org.ecom.ecomsboot.model.Review;
import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.request.ReviewRequest;
import org.ecom.ecomsboot.service.ReviewService;
import org.ecom.ecomsboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Review> createReview(@RequestBody ReviewRequest req,
                                               @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserByJwt(jwt);
        Review review = reviewService.createReview(req, user);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductsReview(@PathVariable Long productId) throws UserException, ProductException {
        List<Review> reviews = reviewService.getProductsReview(productId);
        return new ResponseEntity<>(reviews, HttpStatus.CREATED);
    }
}

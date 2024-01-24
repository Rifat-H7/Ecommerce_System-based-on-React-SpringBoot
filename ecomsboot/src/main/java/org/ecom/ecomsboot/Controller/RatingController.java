package org.ecom.ecomsboot.Controller;

import org.ecom.ecomsboot.exception.ProductException;
import org.ecom.ecomsboot.exception.UserException;
import org.ecom.ecomsboot.model.Rating;
import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.request.RatingRequest;
import org.ecom.ecomsboot.service.RatingService;
import org.ecom.ecomsboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating")
public class RatingController {
    @Autowired
    private RatingService ratingService;
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(@RequestBody RatingRequest req,
                                               @RequestHeader("Authorization") String jwt) throws UserException, ProductException {
        User user = userService.findUserByJwt(jwt);
        Rating rating = ratingService.createRating(req, user);
        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
    }
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Rating>> getProductsRating(@PathVariable Long productId,
                @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
        User user=userService.findUserByJwt(jwt);
        List<Rating> ratings=ratingService.getProductsRating(productId);
        return new ResponseEntity<>(ratings,HttpStatus.CREATED);
    }
}

package org.ecom.ecomsboot.service;

import org.ecom.ecomsboot.exception.ProductException;
import org.ecom.ecomsboot.model.Cart;
import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.request.AddItemRequest;

public interface CartService {
    public Cart createCart(User user);

    public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
    public Cart findUserCart(Long userId);
}

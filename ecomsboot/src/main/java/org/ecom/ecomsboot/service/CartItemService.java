package org.ecom.ecomsboot.service;

import org.ecom.ecomsboot.exception.CartItemException;
import org.ecom.ecomsboot.exception.UserException;
import org.ecom.ecomsboot.model.Cart;
import org.ecom.ecomsboot.model.CartItem;
import org.ecom.ecomsboot.model.Product;

public interface CartItemService {
    public CartItem createCartItem(CartItem cartItem);

    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem)throws CartItemException, UserException;
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
    public CartItem findCartItemById(Long cartItemId) throws CartItemException;

}

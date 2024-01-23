package org.ecom.ecomsboot.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.ecom.ecomsboot.exception.CartItemException;
import org.ecom.ecomsboot.exception.UserException;
import org.ecom.ecomsboot.model.Cart;
import org.ecom.ecomsboot.model.CartItem;
import org.ecom.ecomsboot.model.Product;
import org.ecom.ecomsboot.model.User;
import org.ecom.ecomsboot.repository.CartItemRepository;
import org.ecom.ecomsboot.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CartItemServiceImpl implements CartItemService{
    private UserService userService;
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;


    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
        cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
        CartItem item=findCartItemById(id);
        User user=userService.findUserById(item.getUserId());
        if(user.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity()*item.getProduct().getPrice());
            item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
        }
        return cartItemRepository.save(item);

    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return cartItemRepository.isCartItemExist(cart,product,size,userId);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        CartItem cartItem=findCartItemById(cartItemId);
        User user=userService.findUserById(cartItem.getUserId());
        User reqUser=userService.findUserById(userId);
        if(user.getId().equals(reqUser.getId())){
            cartItemRepository.deleteById(cartItemId);
        }
        else{
            throw new CartItemException("Cart Item not found");
        }

    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem>opt=cartItemRepository.findById(cartItemId);
        if(opt.isPresent()){
            return opt.get();
        }
        else{
            throw new CartItemException("Cart Item not found");
        }
    }
}

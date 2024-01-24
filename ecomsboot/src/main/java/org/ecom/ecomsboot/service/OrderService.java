package org.ecom.ecomsboot.service;

import org.ecom.ecomsboot.exception.OrderException;
import org.ecom.ecomsboot.model.Address;
import org.ecom.ecomsboot.model.Order;
import org.ecom.ecomsboot.model.User;

import java.util.List;

public interface OrderService {
    public Order createOrder(User user, Address shippingAddress);
    public Order findOrderById(Long orderId) throws OrderException;

    public List<Order> usersOrderHistory(Long userId);

    public Order placedOrder(Long orderId) throws OrderException;

    public Order confirmedOrder(Long orderId)throws OrderException;

    public Order shippedOrder(Long orderId) throws OrderException;

    public Order deliveredOrder(Long orderId) throws OrderException;

    public Order cancelOrder(Long orderId) throws OrderException;
    public List<Order>getAllOrders();

    public void deleteOrder(Long orderId) throws OrderException;
}

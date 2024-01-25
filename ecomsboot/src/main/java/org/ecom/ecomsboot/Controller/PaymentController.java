//package org.ecom.ecomsboot.Controller;
//
//import org.ecom.ecomsboot.exception.OrderException;
//import org.ecom.ecomsboot.model.Order;
//import org.ecom.ecomsboot.service.OrderService;
//import org.ecom.ecomsboot.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestHeader;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//public class PaymentController {
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private OrderRepository orderRepository;
//    @Autowired
//    private OrderService orderService;
//
//    public ResponseEntity<PaymentLinkResponse> createPaymentLink(@PathVariable Long orderId,
//                                                                 @RequestHeader("Authorization") String jwt) throws OrderException {
//        Order order = orderService.findOrderById(orderId);
//
//    }
//
//}

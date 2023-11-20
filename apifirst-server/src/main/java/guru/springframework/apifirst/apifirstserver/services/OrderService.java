package guru.springframework.apifirst.apifirstserver.services;

import guru.springframework.apifirst.model.Order;
import guru.springframework.apifirst.model.OrderCreate;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface OrderService {
    List<Order> listOrders();

    Order getOrderById(UUID orderId);

    Order saveNewOrder(OrderCreate orderCreate);
}

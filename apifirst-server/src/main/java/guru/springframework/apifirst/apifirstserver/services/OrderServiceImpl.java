package guru.springframework.apifirst.apifirstserver.services;

import guru.springframework.apifirst.apifirstserver.repositories.OrderRepository;
import guru.springframework.apifirst.model.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

/**
 * Created by jt, Spring Framework Guru.
 */
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> listOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .toList();
    }

    @Override
    public Order getOrderById(UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow();
    }
}

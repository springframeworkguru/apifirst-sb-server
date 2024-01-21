package guru.springframework.apifirst.apifirstserver.services;

import guru.springframework.apifirst.apifirstserver.domain.*;
import guru.springframework.apifirst.apifirstserver.mappers.OrderMapper;
import guru.springframework.apifirst.apifirstserver.repositories.CustomerRepository;
import guru.springframework.apifirst.apifirstserver.repositories.OrderRepository;
import guru.springframework.apifirst.apifirstserver.repositories.ProductRepository;
import guru.springframework.apifirst.model.OrderCreateDto;
import guru.springframework.apifirst.model.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderDto saveNewOrder(OrderCreateDto orderCreate) {
        Customer orderCustomer = customerRepository.findById(orderCreate.getCustomerId()).orElseThrow();

        Order.OrderBuilder builder = Order.builder()
                .customer(orderCustomer)
                .orderStatus(OrderStatusEnum.NEW);

        List<OrderLine> orderLines = new ArrayList<>();

        orderCreate.getOrderLines()
                .forEach(orderLineCreate -> {
                    Product product = productRepository.findById(orderLineCreate.getProductId()).orElseThrow();

                    orderLines.add(OrderLine.builder()
                            .product(product)
                            .orderQuantity(orderLineCreate.getOrderQuantity())
                            .build());
                });

        Order savedOrder = orderRepository.saveAndFlush(builder.orderLines(orderLines).build());

        return orderMapper.orderToDto(savedOrder);

    }

    @Override
    public List<OrderDto> listOrders() {
        return StreamSupport.stream(orderRepository.findAll().spliterator(), false)
                .map(orderMapper::orderToDto)
                .toList();

    }

    @Override
    public OrderDto getOrderById(UUID orderId) {
      return orderMapper.orderToDto(orderRepository.findById(orderId).orElseThrow());
    }
}

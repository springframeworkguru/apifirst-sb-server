package guru.springframework.apifirst.apifirstserver.services;

import guru.springframework.apifirst.apifirstserver.domain.Order;
import guru.springframework.apifirst.apifirstserver.mappers.OrderMapper;
import guru.springframework.apifirst.apifirstserver.repositories.OrderRepository;
import guru.springframework.apifirst.model.OrderCreateDto;
import guru.springframework.apifirst.model.OrderDto;
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
    private final OrderMapper orderMapper;

    @Override
    public OrderDto saveNewOrder(OrderCreateDto orderCreate) {
        Order savedOrder = orderRepository.saveAndFlush(orderMapper.dtoToOrder(orderCreate));

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

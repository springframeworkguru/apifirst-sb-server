package guru.springframework.apifirst.apifirstserver.mappers;

import guru.springframework.apifirst.apifirstserver.domain.Order;
import guru.springframework.apifirst.model.OrderDto;
import org.mapstruct.Mapper;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface OrderMapper {

    Order dtoToOrder(OrderDto orderDto);

    OrderDto orderToDto(Order order);
}

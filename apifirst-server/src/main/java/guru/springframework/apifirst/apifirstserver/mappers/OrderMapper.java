package guru.springframework.apifirst.apifirstserver.mappers;

import guru.springframework.apifirst.apifirstserver.domain.Order;
import guru.springframework.apifirst.model.OrderCreateDto;
import guru.springframework.apifirst.model.OrderDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
@DecoratedWith(OrderMapperDecorator.class)
public interface OrderMapper {
    @Mapping(target = "shipmentInfo", ignore = true)
    @Mapping(target = "selectedPaymentMethod", ignore = true)
    @Mapping(target = "orderStatus", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "customer", ignore = true)
    Order dtoToOrder(OrderCreateDto orderDto);

    @Mapping(target = "selectedPaymentMethod", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    Order dtoToOrder(OrderDto orderDto);

    OrderDto orderToDto(Order order);
}

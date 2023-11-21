package guru.springframework.apifirst.apifirstserver.repositories;

import guru.springframework.apifirst.model.OrderDto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface OrderRepository extends CrudRepository<OrderDto, UUID> {
}

package guru.springframework.apifirst.apifirstserver.repositories;

import guru.springframework.apifirst.apifirstserver.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface OrderRepository extends JpaRepository<Order, UUID> {
}

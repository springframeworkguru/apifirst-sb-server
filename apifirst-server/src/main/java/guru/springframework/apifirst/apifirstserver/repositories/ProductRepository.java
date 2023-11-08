package guru.springframework.apifirst.apifirstserver.repositories;

import guru.springframework.apifirst.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface ProductRepository extends CrudRepository<Product, UUID> {
}

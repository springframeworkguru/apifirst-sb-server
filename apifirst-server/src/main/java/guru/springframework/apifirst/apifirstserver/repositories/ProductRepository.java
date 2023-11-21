package guru.springframework.apifirst.apifirstserver.repositories;

import guru.springframework.apifirst.model.ProductDto;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface ProductRepository extends CrudRepository<ProductDto, UUID> {
}

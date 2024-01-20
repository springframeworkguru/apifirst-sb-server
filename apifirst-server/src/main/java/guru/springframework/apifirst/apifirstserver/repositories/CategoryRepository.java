package guru.springframework.apifirst.apifirstserver.repositories;

import guru.springframework.apifirst.apifirstserver.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Optional<Category> findByCategoryCode(String categoryCode);
}

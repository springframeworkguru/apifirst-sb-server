package guru.springframework.apifirst.apifirstserver.repositories;

import guru.springframework.apifirst.apifirstserver.domain.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    //@Transactional
    @Test
    void testImagePersistence() {
        Product product = productRepository.findAll().iterator().next();

        assertNotNull(product);
        assertNotNull(product.getImages());
        assertTrue(product.getImages().size() > 0);

    }
}
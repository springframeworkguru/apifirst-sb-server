package guru.springframework.apifirst.apifirstserver.services;

import guru.springframework.apifirst.model.Product;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface ProductService {
    List<Product> listProducts();

    Product getProductById(UUID productId);

    Product saveNewProduct(Product product);
}

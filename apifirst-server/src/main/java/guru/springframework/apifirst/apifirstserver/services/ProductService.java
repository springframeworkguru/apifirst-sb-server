package guru.springframework.apifirst.apifirstserver.services;

import guru.springframework.apifirst.model.ProductDto;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface ProductService {
    List<ProductDto> listProducts();

    ProductDto getProductById(UUID productId);

    ProductDto saveNewProduct(ProductDto product);
}

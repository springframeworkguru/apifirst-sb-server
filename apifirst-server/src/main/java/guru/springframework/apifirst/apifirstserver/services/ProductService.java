package guru.springframework.apifirst.apifirstserver.services;

import guru.springframework.apifirst.model.ProductCreateDto;
import guru.springframework.apifirst.model.ProductDto;
import guru.springframework.apifirst.model.ProductPatchDto;
import guru.springframework.apifirst.model.ProductUpdateDto;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface ProductService {

    ProductDto updateProduct(UUID productId, ProductUpdateDto product);
    List<ProductDto> listProducts();

    ProductDto getProductById(UUID productId);

    ProductDto saveNewProduct(ProductCreateDto product);

    ProductDto patchProduct(UUID productId, ProductPatchDto product);
}

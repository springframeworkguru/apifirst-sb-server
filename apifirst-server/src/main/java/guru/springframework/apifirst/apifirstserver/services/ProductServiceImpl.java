package guru.springframework.apifirst.apifirstserver.services;

import guru.springframework.apifirst.apifirstserver.repositories.ProductRepository;
import guru.springframework.apifirst.model.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductDto saveNewProduct(ProductDto product) {
      //  return productRepository.save(product);
        return null;
    }

    @Override
    public List<ProductDto> listProducts() {
//        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
//                .toList();
        return null;
    }

    @Override
    public ProductDto getProductById(UUID productId) {
        //return productRepository.findById(productId).orElseThrow();
        return null;
    }
}

package guru.springframework.apifirst.apifirstserver.services;

import guru.springframework.apifirst.apifirstserver.domain.Product;
import guru.springframework.apifirst.apifirstserver.mappers.ProductMapper;
import guru.springframework.apifirst.apifirstserver.repositories.ProductRepository;
import guru.springframework.apifirst.model.ProductCreateDto;
import guru.springframework.apifirst.model.ProductDto;
import guru.springframework.apifirst.model.ProductPatchDto;
import guru.springframework.apifirst.model.ProductUpdateDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.StreamSupport;

/**
 * Created by jt, Spring Framework Guru.
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public void deleteProduct(UUID productId) {
        productRepository.findById(productId).ifPresentOrElse(productRepository::delete, () -> {
            throw new NotFoundException("Product Not Found");
        });
    }

    @Override
    public ProductDto patchProduct(UUID productId, ProductPatchDto product) {
        Product existingProduct = productRepository.findById(productId).orElseThrow();
        productMapper.patchProduct(product, existingProduct);
        return productMapper.productToDto(productRepository.save(existingProduct));
    }

    @Override
    public ProductDto updateProduct(UUID productId, ProductUpdateDto product) {
        Product existingProduct = productRepository.findById(productId).orElseThrow();
        productMapper.updateProduct(product, existingProduct);
        return productMapper.productToDto(productRepository.save(existingProduct));
    }

    @Override
    public ProductDto saveNewProduct(ProductCreateDto product) {
       return productMapper.productToDto(productRepository.save(productMapper.dtoToProduct(product)));
    }

    @Override
    public List<ProductDto> listProducts() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(productMapper::productToDto)
                .toList();
    }

    @Override
    public ProductDto getProductById(UUID productId) {
        return productMapper.productToDto(productRepository.findById(productId).orElseThrow(NotFoundException::new));
    }
}











package guru.springframework.apifirst.apifirstserver.mappers;

import guru.springframework.apifirst.apifirstserver.domain.Category;
import guru.springframework.apifirst.apifirstserver.domain.Product;
import guru.springframework.apifirst.apifirstserver.repositories.CategoryRepository;
import guru.springframework.apifirst.model.ProductCreateDto;
import guru.springframework.apifirst.model.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jt, Spring Framework Guru.
 */
public abstract class ProductMapperDecorator implements ProductMapper {

    @Autowired
    @Qualifier("delegate")
    private ProductMapper productMapperDelegate;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Product dtoToProduct(ProductDto productDto) {
        return productMapperDelegate.dtoToProduct(productDto);
    }

    @Override
    public Product dtoToProduct(ProductCreateDto productCreateDto) {
        if (productCreateDto != null) {
            Product product = productMapperDelegate.dtoToProduct(productCreateDto);

            if (productCreateDto.getCategories() != null) {
                List<Category> categories = new ArrayList<>();

                productCreateDto.getCategories().forEach(categoryCode -> {
                    categoryRepository.findByCategoryCode(categoryCode).ifPresent(categories::add);
                });

                product.setCategories(categories);

                return product;
            }
        }

        return null;
    }

    @Override
    public ProductDto productToDto(Product product) {
        return productMapperDelegate.productToDto(product);
    }
}















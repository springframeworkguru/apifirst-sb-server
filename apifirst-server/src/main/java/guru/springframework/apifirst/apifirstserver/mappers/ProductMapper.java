package guru.springframework.apifirst.apifirstserver.mappers;

import guru.springframework.apifirst.apifirstserver.domain.Product;
import guru.springframework.apifirst.model.ProductCreateDto;
import guru.springframework.apifirst.model.ProductDto;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
@DecoratedWith(ProductMapperDecorator.class)
public interface ProductMapper {

    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    Product dtoToProduct(ProductDto productDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "categories", ignore = true)
    Product dtoToProduct(ProductCreateDto productCreateDto);

    ProductDto productToDto(Product product);
}

package guru.springframework.apifirst.apifirstserver.mappers;

import guru.springframework.apifirst.model.ProductImageUpdateDto;
import guru.springframework.apifirst.apifirstserver.domain.Image;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface ImageMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    void updateImage(ProductImageUpdateDto image, @MappingTarget Image target);
}

package guru.springframework.apifirst.apifirstserver.mappers;

import guru.springframework.apifirst.apifirstserver.domain.Category;
import guru.springframework.apifirst.model.CategoryDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface CategoryMapper {
    CategoryDto categoryToCategoryDto(Category category);

    @Mapping(target = "products", ignore = true)
    Category categoryDtoToCategory(CategoryDto categoryDto);
}

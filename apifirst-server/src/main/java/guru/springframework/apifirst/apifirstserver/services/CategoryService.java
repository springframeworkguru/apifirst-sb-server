package guru.springframework.apifirst.apifirstserver.services;

import guru.springframework.apifirst.model.CategoryDto;

import java.util.List;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface CategoryService {

    List<CategoryDto> listCategories();
}

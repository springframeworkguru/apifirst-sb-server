package guru.springframework.apifirst.apifirstserver.controllers;

import guru.springframework.apifirst.apifirstserver.services.CategoryService;
import guru.springframework.apifirst.model.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static guru.springframework.apifirst.apifirstserver.controllers.CategoryController.BASE_URL;


/**
 * Created by jt, Spring Framework Guru.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class CategoryController {
    public static final String BASE_URL = "/v1/categories";

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories(){
        return ResponseEntity.ok(categoryService.listCategories());
    }

}

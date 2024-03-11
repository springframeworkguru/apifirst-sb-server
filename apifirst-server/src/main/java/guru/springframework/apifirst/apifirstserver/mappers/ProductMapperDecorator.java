package guru.springframework.apifirst.apifirstserver.mappers;

import guru.springframework.apifirst.apifirstserver.domain.Category;
import guru.springframework.apifirst.apifirstserver.domain.Image;
import guru.springframework.apifirst.apifirstserver.domain.Product;
import guru.springframework.apifirst.apifirstserver.repositories.CategoryRepository;
import guru.springframework.apifirst.apifirstserver.repositories.ImageRepository;
import guru.springframework.apifirst.model.ProductCreateDto;
import guru.springframework.apifirst.model.ProductDto;
import guru.springframework.apifirst.model.ProductPatchDto;
import guru.springframework.apifirst.model.ProductUpdateDto;
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

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    @Override
    public ProductPatchDto productToPatchDto(Product product) {
        if (product != null) {
            if (product.getCategories() != null) {
                List<String> categoryCodes = new ArrayList<>();

                product.getCategories().forEach(category -> {
                    categoryCodes.add(category.getCategoryCode());
                });

                ProductPatchDto productPatchDto = productMapperDelegate.productToPatchDto(product);
                productPatchDto.setCategories(categoryCodes);

                return productPatchDto;
            }
        }

        return null;
    }

    @Override
    public void patchProduct(ProductPatchDto productPatchDto, Product target) {
        productMapperDelegate.patchProduct(productPatchDto, target);

        if (productPatchDto.getImages() != null) {
            productPatchDto.getImages().forEach(imageDto -> {
                target.getImages().stream().filter(image -> image.getId().equals(imageDto.getId()))
                        .findFirst().ifPresent(image -> {
                            imageMapper.patchImage(imageDto, image);
                        });
            });
        }

        if (productPatchDto.getCategories() != null) {
            List<Category> categories = categoryCodesToCategories(productPatchDto.getCategories());
            target.setCategories(categories);
        }
    }

    @Override
    public ProductUpdateDto productToUpdateDto(Product product) {
        if (product != null) {
            if (product.getCategories() != null) {
                List<String> categoryCodes = new ArrayList<>();

                product.getCategories().forEach(category -> {
                    categoryCodes.add(category.getCategoryCode());
                });

                ProductUpdateDto productUpdateDto = productMapperDelegate.productToUpdateDto(product);
                productUpdateDto.setCategories(categoryCodes);

                return productUpdateDto;
            }
        }

        return null;
    }

    @Override
    public Product dtoToProduct(ProductUpdateDto productUpdateDto) {
        if (productUpdateDto != null) {
            Product product = productMapperDelegate.dtoToProduct(productUpdateDto);

            if (productUpdateDto.getCategories() != null) {
                List<Category> categories = categoryCodesToCategories(productUpdateDto.getCategories());

                product.setCategories(categories);
            }

            if (productUpdateDto.getImages() != null) {
                product.setImages(new ArrayList<>());

                productUpdateDto.getImages().forEach(imageDto -> {
                    if (imageDto.getId() != null ) {
                        imageRepository.findById(imageDto.getId()).ifPresent(image -> {
                            Image existingImage = imageRepository.findById(imageDto.getId()).get();
                            imageMapper.updateImage(imageDto, existingImage);
                            product.getImages().add(existingImage);
                        });
                    }
                });
            }

            return product;
        }

        return null;
    }

    @Override
    public void updateProduct(ProductUpdateDto product, Product target) {
        productMapperDelegate.updateProduct(product, target);

        if (product.getImages() != null) {
            product.getImages().forEach(imageDto -> {
                target.setImages(new ArrayList<>());

                if (imageDto.getId() != null) {
                    imageRepository.findById(imageDto.getId()).ifPresent(image -> {
                        Image existingImage = imageRepository.findById(imageDto.getId()).get();
                        imageMapper.updateImage(imageDto, existingImage);
                        target.getImages().add(existingImage);
                    });
                }
            });
        } else {
            target.setImages(new ArrayList<>());
        }

        if (product.getCategories() != null) {
            List<Category> categories = categoryCodesToCategories(product.getCategories());
            target.setCategories(categories);
        }
    }

    //list of string to list of category
    private List<Category> categoryCodesToCategories(List<String> categoryCodes) {
        List<Category> categories = new ArrayList<>();

        categoryCodes.forEach(categoryCode -> {
            categoryRepository.findByCategoryCode(categoryCode).ifPresent(categories::add);
        });

        return categories;
    }

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















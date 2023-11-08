package guru.springframework.apifirst.apifirstserver.repositories;

import guru.springframework.apifirst.model.Category;
import guru.springframework.apifirst.model.Customer;
import guru.springframework.apifirst.model.Dimentions;
import guru.springframework.apifirst.model.Product;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by jt, Spring Framework Guru.
 */
@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private final Map<UUID, Product> entityMap = new HashMap<>();

    @Override
    public <S extends Product> S save(S entity) {

        Product.ProductBuilder builder = Product.builder();

        builder.id(UUID.randomUUID())
                .description(entity.getDescription())
                .cost(entity.getCost())
                .price(entity.getPrice())
                .dateCreated(OffsetDateTime.now())
                .dateUpdated(OffsetDateTime.now());

        if (entity.getCategories() != null) {
            builder.categories(entity.getCategories().stream()
                    .map(category -> {
                        return Category.builder()
                                .id(UUID.randomUUID())
                                .category(category.getCategory())
                                .description(category.getDescription())
                                .dateCreated(OffsetDateTime.now())
                                .dateUpdated(OffsetDateTime.now())
                                .build();
                    })
                    .collect(Collectors.toList()));
        }

        if (entity.getImages() != null) {
            builder.images(entity.getImages().stream()
                    .map(image -> {
                        return guru.springframework.apifirst.model.Image.builder()
                                .id(UUID.randomUUID())
                                .url(image.getUrl())
                                .altText(image.getAltText())
                                .dateCreated(OffsetDateTime.now())
                                .dateUpdated(OffsetDateTime.now())
                                .build();
                    })
                    .collect(Collectors.toList()));
        }

        if (entity.getDimentions() != null) {
            builder.dimentions(Dimentions.builder()
                    .length(entity.getDimentions().getLength())
                    .width(entity.getDimentions().getWidth())
                    .height(entity.getDimentions().getHeight())
                    .build());
        }

        Product product = builder.build();

        entityMap.put(product.getId(), product);
        return (S) product;
    }

    @Override
    public <S extends Product> Iterable<S> saveAll(Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Product> findById(UUID uuid) {
        return Optional.of(entityMap.get(uuid));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return entityMap.get(uuid) != null;
    }

    @Override
    public Iterable<Product> findAll() {
        return entityMap.values();
    }

    @Override
    public Iterable<Product> findAllById(Iterable<UUID> uuids) {
        return StreamSupport.stream(uuids.spliterator(), false)
                .map(this::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    @Override
    public long count() {
        return entityMap.size();
    }

    @Override
    public void deleteById(UUID uuid) {
        entityMap.remove(uuid);
    }

    @Override
    public void delete(Product entity) {
        entityMap.remove(entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {
        uuids.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends Product> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        entityMap.clear();
    }
}

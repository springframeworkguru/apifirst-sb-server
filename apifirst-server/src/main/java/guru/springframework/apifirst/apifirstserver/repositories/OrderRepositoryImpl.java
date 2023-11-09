package guru.springframework.apifirst.apifirstserver.repositories;

import guru.springframework.apifirst.model.Order;
import guru.springframework.apifirst.model.OrderLine;
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
public class OrderRepositoryImpl implements OrderRepository {

    private final Map<UUID, Order> entityMap = new HashMap<>();

    @Override
    public <S extends Order> S save(S entity) {
        Order.OrderBuilder builder = Order.builder();

        builder.id(UUID.randomUUID())
                .orderStatus(entity.getOrderStatus())
                .shipmentInfo(entity.getShipmentInfo())
                .dateCreated(OffsetDateTime.now())
                .dateUpdated(OffsetDateTime.now());

        if (entity.getCustomer() != null) {
            builder.customer(entity.getCustomer());
        }

        if (entity.getOrderLines() != null){
            builder.orderLines(entity.getOrderLines().stream()
                    .map(orderLine -> {
                        return OrderLine.builder()
                                .id(UUID.randomUUID())
                                .product(orderLine.getProduct()) //might cause NPE
                                .orderQuantity(orderLine.getOrderQuantity())
                                .shipQuantity(orderLine.getShipQuantity())
                                .dateCreated(OffsetDateTime.now())
                                .dateUpdated(OffsetDateTime.now())
                                .build();
                    })
                    .collect(Collectors.toList()));
        }

        Order savedEntity = builder.build();
        entityMap.put(savedEntity.getId(), savedEntity);
        return (S) savedEntity;
    }

    @Override
    public <S extends Order> Iterable<S> saveAll(Iterable<S> entities) {
        return StreamSupport.stream(entities.spliterator(), false)
                .map(this::save)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> findById(UUID uuid) {
        return Optional.of(entityMap.get(uuid));
    }

    @Override
    public boolean existsById(UUID uuid) {
        return entityMap.get(uuid) != null;
    }

    @Override
    public Iterable<Order> findAll() {
        return entityMap.values();
    }

    @Override
    public Iterable<Order> findAllById(Iterable<UUID> uuids) {
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
    public void delete(Order entity) {
        entityMap.remove(entity.getId());
    }

    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {
        uuids.forEach(this::deleteById);
    }

    @Override
    public void deleteAll(Iterable<? extends Order> entities) {
        entities.forEach(this::delete);
    }

    @Override
    public void deleteAll() {
        entityMap.clear();
    }
}

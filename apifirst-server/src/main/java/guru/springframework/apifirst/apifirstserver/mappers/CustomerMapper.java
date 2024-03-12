package guru.springframework.apifirst.apifirstserver.mappers;

import guru.springframework.apifirst.apifirstserver.domain.Customer;
import guru.springframework.apifirst.model.CustomerDto;
import guru.springframework.apifirst.model.CustomerPatchDto;
import org.mapstruct.*;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
@DecoratedWith(CustomerMapperDecorator.class)
public interface CustomerMapper {

    CustomerPatchDto customerToPatchDto(Customer customer);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "shipToAddress.id", ignore = true)
    @Mapping(target = "billToAddress.id", ignore = true)
    @Mapping(target = "paymentMethods", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void patchCustomer(CustomerPatchDto customerPatchDto, @MappingTarget Customer target);
    CustomerDto customerToDto(Customer customer);

    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    Customer dtoToCustomer(CustomerDto customerDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    void updateCustomer(CustomerDto customerDto, @MappingTarget Customer customer);
}

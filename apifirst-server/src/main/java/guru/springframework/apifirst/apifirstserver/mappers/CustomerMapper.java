package guru.springframework.apifirst.apifirstserver.mappers;

import guru.springframework.apifirst.apifirstserver.domain.Customer;
import guru.springframework.apifirst.model.CustomerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface CustomerMapper {

    CustomerDto customerToDto(Customer customer);

    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    Customer dtoToCustomer(CustomerDto customerDto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    Customer updateCustomer(CustomerDto customerDto, @MappingTarget Customer customer);
}

package guru.springframework.apifirst.apifirstserver.mappers;

import guru.springframework.apifirst.apifirstserver.domain.PaymentMethod;
import guru.springframework.apifirst.model.CustomerPaymentMethodPatchDto;
import guru.springframework.apifirst.model.PaymentMethodDto;
import org.mapstruct.*;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface PaymentMethodMapper {

    PaymentMethodDto paymentMethodToDto(PaymentMethod paymentMethod);

    @Mapping(target = "dateUpdated", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "customer", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void updatePaymentMethod(CustomerPaymentMethodPatchDto customerPaymentMethodPatchDto,
                             @MappingTarget PaymentMethod paymentMethod);
}

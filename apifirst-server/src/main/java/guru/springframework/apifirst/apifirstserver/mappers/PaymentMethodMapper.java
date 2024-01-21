package guru.springframework.apifirst.apifirstserver.mappers;

import guru.springframework.apifirst.apifirstserver.domain.PaymentMethod;
import guru.springframework.apifirst.model.PaymentMethodDto;
import org.mapstruct.Mapper;

/**
 * Created by jt, Spring Framework Guru.
 */
@Mapper
public interface PaymentMethodMapper {

    PaymentMethodDto paymentMethodToDto(PaymentMethod paymentMethod);
}

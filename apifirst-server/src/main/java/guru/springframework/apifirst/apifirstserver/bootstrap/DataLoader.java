package guru.springframework.apifirst.apifirstserver.bootstrap;

import guru.springframework.apifirst.apifirstserver.repositories.CustomerRepository;
import guru.springframework.apifirst.model.Address;
import guru.springframework.apifirst.model.Customer;
import guru.springframework.apifirst.model.Name;
import guru.springframework.apifirst.model.PaymentMethod;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

        private final CustomerRepository customerRepository;

        @Override
        public void run(String... args) throws Exception {

            Address address1 = Address.builder()
                    .addressLine1("1234 W Some Street")
                    .city("Some City")
                    .state("FL")
                    .zip("33701")
                    .build();

            Customer customer1 = Customer.builder()
                    .name(Name.builder()
                            .firstName("John")
                            .lastName("Thompson")
                            .build())
                    .billToAddress(address1)
                    .shipToAddress(address1)
                    .email("john@springframework.guru")
                    .phone("800-555-1212")
                    .paymentMethods(List.of(PaymentMethod.builder()
                                    .cardNumber(12341234)
                                    .expiryMonth(12)
                                    .expiryYear(26)
                            .build()))
                    .build();

            Address address2 = Address.builder()
                    .addressLine1("1234 W Some Street")
                    .city("Some City")
                    .state("FL")
                    .zip("33701")
                    .build();

            Customer customer2 = Customer.builder()
                    .name(Name.builder()
                            .firstName("Jim")
                            .lastName("Smith")
                            .build())
                    .billToAddress(address2)
                    .shipToAddress(address2)
                    .email("jim@springframework.guru")
                    .phone("800-555-1212")
                    .paymentMethods(List.of(PaymentMethod.builder()
                            .cardNumber(1234888)
                            .expiryMonth(12)
                            .expiryYear(26)
                            .build()))
                    .build();

            customerRepository.save(customer1);
            customerRepository.save(customer2);
        }
}












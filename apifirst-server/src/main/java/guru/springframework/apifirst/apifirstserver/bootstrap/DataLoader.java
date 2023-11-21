package guru.springframework.apifirst.apifirstserver.bootstrap;

import guru.springframework.apifirst.apifirstserver.repositories.CustomerRepository;
import guru.springframework.apifirst.apifirstserver.repositories.OrderRepository;
import guru.springframework.apifirst.apifirstserver.repositories.ProductRepository;
import guru.springframework.apifirst.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by jt, Spring Framework Guru.
 */
@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

        private final CustomerRepository customerRepository;
        private final ProductRepository productRepository;
        private final OrderRepository orderRepository;

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
                                    .displayName("My Card")
                                    .cardNumber(12341234)
                                    .expiryMonth(12)
                                    .expiryYear(26)
                                    .cvv(123)
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
                            .displayName("My Other Card")
                            .cardNumber(1234888)
                            .expiryMonth(12)
                            .expiryYear(26)
                            .cvv(456)
                            .build()))
                    .build();

            Customer savedCustomer1 = customerRepository.save(customer1);
            Customer savedCustomer2 = customerRepository.save(customer2);

            Product product1 = Product.builder()
                    .description("Product 1")
                    .categories(List.of(Category.builder()
                                    .category("Category 1")
                                    .description("Category 1 Description")
                            .build()))
                    .cost("12.99")
                    .price("14.99")
                    .dimensions(Dimensions.builder()
                            .height(1)
                            .length(2)
                            .width(3)
                            .build())
                    .images(List.of(Image.builder()
                                    .url("http://example.com/image1")
                                    .altText("Image 1")
                            .build()))
                    .build();

            Product product2 = Product.builder()
                    .description("Product 2")
                    .categories(List.of(Category.builder()
                            .category("Category 2")
                            .description("Category 2 Description")
                            .build()))
                    .cost("12.99")
                    .price("14.99")
                    .dimensions(Dimensions.builder()
                            .height(1)
                            .length(2)
                            .width(3)
                            .build())
                    .images(List.of(Image.builder()
                            .url("http://example.com/image2")
                            .altText("Image 2")
                            .build()))
                    .build();

            Product savedProduct1 = productRepository.save(product1);
            Product savedProduct2 = productRepository.save(product2);

            Order order1 = Order.builder()
                    .customer(OrderCustomer.builder()
                            .id(savedCustomer1.getId())
                            .name(savedCustomer1.getName())
                            .billToAddress(savedCustomer1.getBillToAddress())
                            .shipToAddress(savedCustomer1.getShipToAddress())
                            .phone(savedCustomer1.getPhone())
                            .selectedPaymentMethod(savedCustomer1.getPaymentMethods().get(0))
                            .build())
                    .orderStatus(Order.OrderStatusEnum.NEW)
                    .shipmentInfo("shipment info")
                    .orderLines(List.of(OrderLine.builder()
                            .product(OrderProduct.builder()
                                    .id(savedProduct1.getId())
                                    .description(product1.getDescription())
                                    .price(product1.getPrice())
                                    .build())
                            .orderQuantity(1)
                            .shipQuantity(1)
                            .build(),
                            OrderLine.builder()
                                    .product(OrderProduct.builder()
                                            .id(savedProduct2.getId())
                                            .description(product2.getDescription())
                                            .price(product1.getPrice())
                                            .build())
                                    .orderQuantity(1)
                                    .shipQuantity(1)
                                    .build()))
                    .build();

            Order order2 = Order.builder()
                    .customer(OrderCustomer.builder()
                            .id(savedCustomer2.getId())
                            .name(savedCustomer2.getName())
                            .billToAddress(savedCustomer2.getBillToAddress())
                            .shipToAddress(savedCustomer2.getShipToAddress())
                            .phone(savedCustomer2.getPhone())
                            .selectedPaymentMethod(savedCustomer2.getPaymentMethods().get(0))
                            .build())
                    .orderStatus(Order.OrderStatusEnum.NEW)
                    .shipmentInfo("shipment info #2")
                    .orderLines(List.of(OrderLine.builder()
                                    .product(OrderProduct.builder()
                                            .id(savedProduct1.getId())
                                            .description(product1.getDescription())
                                            .price(product1.getPrice())
                                            .build())
                                    .orderQuantity(1)
                                    .shipQuantity(1)
                                    .build(),
                            OrderLine.builder()
                                    .product(OrderProduct.builder()
                                            .id(savedProduct2.getId())
                                            .description(product2.getDescription())
                                            .price(product1.getPrice())
                                            .build())
                                    .orderQuantity(1)
                                    .shipQuantity(1)
                                    .build()))
                    .build();

            orderRepository.save(order1);
            orderRepository.save(order2);
        }
}












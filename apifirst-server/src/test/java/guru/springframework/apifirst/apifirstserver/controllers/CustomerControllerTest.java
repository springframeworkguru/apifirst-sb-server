package guru.springframework.apifirst.apifirstserver.controllers;

import guru.springframework.apifirst.apifirstserver.domain.Customer;
import guru.springframework.apifirst.model.AddressDto;
import guru.springframework.apifirst.model.CustomerDto;
import guru.springframework.apifirst.model.NameDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by jt, Spring Framework Guru.
 */
@SpringBootTest
public class CustomerControllerTest extends BaseTest {


    //test update customer
    @Transactional
    @DisplayName("Test Update Customer")
    @Test
    void testUpdateCustomer() throws Exception {
        Customer customer = customerRepository.findAll().iterator().next();

        customer.getName().setFirstName("Updated");
        customer.getName().setLastName("Updated2");
        customer.getPaymentMethods().get(0).setDisplayName("NEW NAME");

        mockMvc.perform(put(CustomerController.BASE_URL + "/{customerId}", testCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerMapper.customerToDto(customer))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name.firstName", equalTo("Updated")))
                .andExpect(jsonPath("$.name.lastName", equalTo("Updated2")))
                .andExpect(jsonPath("$.paymentMethods[0].displayName", equalTo("NEW NAME")));
    }

    @DisplayName("Test Create Customer")
    @Test
    void testCreateCustomer() throws Exception {
        CustomerDto customer = CustomerDto.builder()
                .name(NameDto.builder()
                        .lastName("Doe")
                        .firstName("John")
                        .build())
                .phone("555-555-5555")
                .email("john@example.com")
                .shipToAddress(AddressDto.builder()
                        .addressLine1("123 Main St")
                        .city("Denver")
                        .state("CO")
                        .zip("80216")
                        .build())
                .billToAddress(AddressDto.builder()
                        .addressLine1("123 Main St")
                        .city("Denver")
                        .state("CO")
                        .zip("80216")
                        .build())
                .build();

        mockMvc.perform(post(CustomerController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @DisplayName("Get by Id")
    @Test
    void testGetCustomerById() throws Exception {
        mockMvc.perform(get(CustomerController.BASE_URL + "/{customerId}", testCustomer.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testCustomer.getId().toString()));
    }

    @DisplayName("Test List Customers")
    @Test
    void testListCustomers() throws Exception {
        mockMvc.perform(get(CustomerController.BASE_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }
}

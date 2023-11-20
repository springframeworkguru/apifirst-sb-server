package guru.springframework.apifirst.apifirstserver.controllers;

import guru.springframework.apifirst.model.OrderCreate;
import guru.springframework.apifirst.model.OrderLineCreate;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class OrderControllerTest extends BaseTest {

    @Test
    void testCreateOrder() throws Exception {
        OrderCreate orderCreate = OrderCreate.builder()
                .customerId(testCustomer.getId())
                .selectPaymentMethod(testCustomer.getPaymentMethods().get(0).getId())
                .orderLines(Arrays.asList(OrderLineCreate.builder()
                        .productId(testProduct.getId())
                        .orderQuantity(1)
                        .build()))
                .build();

        System.out.println(objectMapper.writeValueAsString(orderCreate));

        mockMvc.perform(post(OrderController.BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderCreate)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
    }

    @Test
    void listOrders() throws Exception {
        mockMvc.perform(get(OrderController.BASE_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", greaterThan(0)));
    }

    @Test
    void getOrderById() throws Exception {
        mockMvc.perform(get(OrderController.BASE_URL + "/{orderId}", testOrder.getId())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(testOrder.getId().toString())));
    }
}
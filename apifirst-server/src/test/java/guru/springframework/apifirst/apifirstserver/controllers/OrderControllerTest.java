package guru.springframework.apifirst.apifirstserver.controllers;


import guru.springframework.apifirst.apifirstserver.domain.Order;
import guru.springframework.apifirst.model.OrderCreateDto;
import guru.springframework.apifirst.model.OrderLineCreateDto;
import guru.springframework.apifirst.model.OrderUpdateDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class OrderControllerTest extends BaseTest {

    @Test
    @Transactional
    void testUpdateOrder() throws Exception {

        Order order = orderRepository.findAll().get(0);

        order.getOrderLines().get(0).setOrderQuantity(222);

        OrderUpdateDto orderUpdate = orderMapper.orderToUpdateDto(order);

        mockMvc.perform(put(OrderController.BASE_URL + "/{orderId}", testOrder.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(orderUpdate))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(testOrder.getId().toString())))
                .andExpect(jsonPath("$.orderLines[0].orderQuantity", equalTo(222)));
    }

    @Test
    @Transactional
    void testCreateOrder() throws Exception {
        OrderCreateDto orderCreate = OrderCreateDto.builder()
                .customerId(testCustomer.getId())
                .selectPaymentMethodId(testCustomer.getPaymentMethods().get(0).getId())
                .orderLines(Arrays.asList(OrderLineCreateDto.builder()
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
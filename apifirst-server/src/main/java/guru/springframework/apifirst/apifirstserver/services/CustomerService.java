package guru.springframework.apifirst.apifirstserver.services;

import guru.springframework.apifirst.model.Customer;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface CustomerService {
    List<Customer> listCustomers();

    Customer getCustomerById(UUID customerId);
}

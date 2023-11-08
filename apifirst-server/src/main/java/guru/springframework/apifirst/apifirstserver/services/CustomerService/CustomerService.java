package guru.springframework.apifirst.apifirstserver.services.CustomerService;

import guru.springframework.apifirst.model.Customer;

import java.util.List;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface CustomerService {
    List<Customer> listCustomers();
}

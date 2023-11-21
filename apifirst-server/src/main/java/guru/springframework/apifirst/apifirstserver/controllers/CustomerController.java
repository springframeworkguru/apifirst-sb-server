package guru.springframework.apifirst.apifirstserver.controllers;

import guru.springframework.apifirst.apifirstserver.services.CustomerService;
import guru.springframework.apifirst.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static guru.springframework.apifirst.apifirstserver.controllers.CustomerController.BASE_URL;

/**
 * Created by jt, Spring Framework Guru.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(BASE_URL)
public class CustomerController {

    public static final String BASE_URL = "/v1/customers";

    private final CustomerService customerService;

    @PostMapping
    ResponseEntity<Void> saveNewCustomer(@RequestBody CustomerDto customer){
        CustomerDto savedCustomer = customerService.saveNewCustomer(customer);
        return ResponseEntity.created(URI.create(BASE_URL + "/" + savedCustomer.getId())).build();
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> listCustomers(){
        return ResponseEntity.ok(customerService.listCustomers());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable("customerId") UUID customerId) {
        return ResponseEntity.ok(customerService.getCustomerById(customerId));
    }
}




















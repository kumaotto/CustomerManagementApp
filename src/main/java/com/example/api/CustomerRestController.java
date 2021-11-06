package com.example.api;

import com.example.domain.Customer;
import com.example.service.CustomerService;
import com.example.service.LoginUserDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;
import java.util.List;

@RestController
// annotation for relative path: api/customers
@RequestMapping("api/customers")
public class CustomerRestController {
  // CustomerService DI
  @Autowired
  CustomerService customerService;

  /**
   * get customers list
   *
   * @return JSON
   */
  @GetMapping
  Page<Customer> getCustomers(@PageableDefault Pageable pageable) {
    Page<Customer> customers = customerService.findAll(pageable);
    return customers;
  }
  // List<Customer> getCustomers() {
  // List<Customer> customers = customerService.findAll();
  // return customers;
  // }

  // get 1 customer
  @GetMapping(path = "{id}")
  Customer getCustomer(@PathVariable Integer id) {
    Customer customer = customerService.findOne(id);
    return customer;
  }

  @PostMapping
  ResponseEntity<Customer> postCustomers(@RequestBody Customer customer, UriComponentsBuilder uriBuilder,
      @AuthenticationPrincipal LoginUserDetails userDetails) {
    Customer created = customerService.create(customer, userDetails.getUser());
    URI location = uriBuilder.path("api/customers/{id}").buildAndExpand(created.getId()).toUri();
    return ResponseEntity.created(location).body(created);
  }

  @PutMapping(path = "{id}")
  Customer putCustomer(@PathVariable Integer id, @RequestBody Customer customer,
      @AuthenticationPrincipal LoginUserDetails userDetails) {
    customer.setId(id);
    return customerService.update(customer, userDetails.getUser());
  }

  @DeleteMapping(path = "{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  void deleteCustomer(@PathVariable Integer id) {
    customerService.delete(id);
  }
}

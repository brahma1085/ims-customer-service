package com.example.customers.controller;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.customers.entity.Customer;
import com.example.customers.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	private final CustomerService service;

	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@GetMapping("/ping")
	public String ping() {
		return "Customers service is running!";
	}

	@GetMapping
	public List<Customer> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public Customer getById(@PathVariable Long id) {
		return service.get(id);
	}

	@PostMapping
	public Customer create(@RequestBody Customer customer) {
		return service.create(customer);
	}

	@PutMapping("/{id}")
	public Customer update(@PathVariable Long id, @RequestBody Customer customer) {
		return service.update(id, customer);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}

	@GetMapping("/me")
	public Map<String, Object> userDetails(@AuthenticationPrincipal Jwt jwt) {
		return Map.of("username", jwt.getClaim("preferred_username"), "email", jwt.getClaim("email"), "roles",
				jwt.getClaim("realm_access"));
	}
}

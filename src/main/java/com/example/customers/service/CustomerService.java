package com.example.customers.service;

import org.springframework.stereotype.Service;
import java.util.List;
import com.example.customers.entity.Customer;
import com.example.customers.repository.CustomerRepository;

@Service
public class CustomerService {

    private final CustomerRepository repo;

    public CustomerService(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<Customer> getAll() { return repo.findAll(); }

    public Customer get(Long id) { return repo.findById(id).orElse(null); }

    public Customer create(Customer customer) { return repo.save(customer); }

    public Customer update(Long id, Customer customer) {
        Customer existing = repo.findById(id).orElse(null);
        if (existing == null) return null;

        existing.setFullName(customer.getFullName());
        existing.setEmail(customer.getEmail());
        return repo.save(existing);
    }

    public void delete(Long id) { repo.deleteById(id); }
}

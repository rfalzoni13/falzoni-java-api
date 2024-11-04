package br.com.falzoni.falzoni_java_api.services.entries;

import br.com.falzoni.falzoni_java_api.domain.entities.entries.Customer;
import br.com.falzoni.falzoni_java_api.repositories.entries.CustomerRepository;
import br.com.falzoni.falzoni_java_api.services.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService extends AbstractService<Customer> {
    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> findAll() {
        return repository.findAll();
    }

    @Override
    public Customer findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void insert(Customer obj) {
        repository.save(obj);
    }

    @Override
    public void update(Customer obj) {
        Optional<Customer> hasCustomer = repository.findById(obj.getId());
        if(hasCustomer.isPresent()) {
            Customer customer = hasCustomer.get();
            customer.setName(obj.getName());
            customer.setDocument(obj.getDocument());
            repository.save(customer);
        }
    }

    @Override
    public void delete(Customer obj) {
        repository.delete(obj);
    }
}

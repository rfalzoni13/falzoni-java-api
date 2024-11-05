package br.com.falzoni.falzoni_java_api.services.entries;

import br.com.falzoni.falzoni_java_api.domain.entities.entries.Customer;
import br.com.falzoni.falzoni_java_api.repositories.entries.CustomerRepository;
import br.com.falzoni.falzoni_java_api.services.base.AbstractService;
import br.com.falzoni.falzoni_java_api.utils.ValidationUtils;
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
        try {
            Validate(obj);
            repository.save(obj);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void update(Customer obj) {
        try {
            Validate(obj);
            Optional<Customer> hasCustomer = repository.findById(obj.getId());
            if (hasCustomer.isPresent()) {
                Customer customer = hasCustomer.get();
                customer.setName(obj.getName());
                customer.setDocument(obj.getDocument());
                repository.save(customer);
            } else {
                throw new Exception("Registro não encontrado");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void delete(Customer obj) {
        repository.delete(obj);
    }

    // private METHODS
    private void Validate(Customer obj) throws Exception {
        if (ValidationUtils.CheckNullValue(obj.getName()))
            throw new Exception("O nome do cliente não foi preenchido corretamente");

        if (ValidationUtils.CheckNullValue(obj.getDocument()))
            throw new Exception("O documento do cliente não foi preenchido corretamente");
    }
}

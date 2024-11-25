package br.com.falzoni.falzoni_java_api.services.register;

import br.com.falzoni.falzoni_java_api.domain.dtos.classes.register.CustomerDTO;
import br.com.falzoni.falzoni_java_api.domain.entities.register.Customer;
import br.com.falzoni.falzoni_java_api.repositories.register.CustomerRepository;
import br.com.falzoni.falzoni_java_api.services.base.AbstractService;
import br.com.falzoni.falzoni_java_api.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerService extends AbstractService<CustomerDTO> {
    @Autowired
    private CustomerRepository repository;

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> list = repository.findAll();
        return list.stream()
                .map(c -> new CustomerDTO(c.getId(), c.getName(), c.getDocument()))
                .toList();
    }

    @Override
    public CustomerDTO findById(UUID id) {
        Customer obj = repository.findById(id).orElse(null);
        if(obj != null) {
            return new CustomerDTO(obj.getId(), obj.getName(), obj.getDocument());
        }
        return null;
    }

    @Override
    public void insert(CustomerDTO obj) {
        try {
            Validate(obj);
            Customer customer = new Customer(obj.getName(), obj.getDocument());
            repository.save(customer);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void update(CustomerDTO obj) {
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
    public void delete(UUID id) {
        try {
            Optional<Customer> optional = repository.findById(id);
            if(optional.isEmpty()) throw new Exception("Registro não encontrado");

            Customer customer = optional.get();
            repository.delete(customer);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    // private METHODS
    private void Validate(CustomerDTO obj) throws Exception {
        if (ValidationUtils.CheckNullValue(obj.getName()))
            throw new Exception("O nome do cliente não foi preenchido corretamente");

        if (ValidationUtils.CheckNullValue(obj.getDocument()))
            throw new Exception("O documento do cliente não foi preenchido corretamente");
    }
}

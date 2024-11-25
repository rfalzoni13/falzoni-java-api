package br.com.falzoni.falzoni_java_api.controllers.entries;

import br.com.falzoni.falzoni_java_api.domain.entities.entries.Customer;
import br.com.falzoni.falzoni_java_api.services.entries.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/customer")
@Tag(name = "Customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("/findAll")
    public List<Customer> findAll() {
        return service.findAll();
    }
}

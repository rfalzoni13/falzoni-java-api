package br.com.falzoni.falzoni_java_api.controllers.register;

import br.com.falzoni.falzoni_java_api.domain.dtos.classes.register.CustomerDTO;
import br.com.falzoni.falzoni_java_api.domain.dtos.classes.security.ResponseDTO;
import br.com.falzoni.falzoni_java_api.services.register.CustomerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/customer")
@Tag(name = "Customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        try {
            List<CustomerDTO> list = this.service.findAll();
            return ResponseEntity.ok(list);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Object> findById(@PathVariable(name = "id") UUID id) {
        try {
            CustomerDTO obj = this.service.findById(id);
            return ResponseEntity.ok(obj);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody CustomerDTO dto) {
        try {
            this.service.insert(dto);
            return ResponseEntity.ok("Registro incluído com sucesso!");
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody CustomerDTO dto) {
        try {
            this.service.update(dto);
            return ResponseEntity.ok("Registro incluído com sucesso!");
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }
}

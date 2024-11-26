package br.com.falzoni.falzoni_java_api.controllers.stock;

import br.com.falzoni.falzoni_java_api.domain.dtos.classes.register.CustomerDTO;
import br.com.falzoni.falzoni_java_api.domain.dtos.classes.security.ResponseDTO;
import br.com.falzoni.falzoni_java_api.domain.dtos.classes.stock.ProductDTO;
import br.com.falzoni.falzoni_java_api.services.stock.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("aoi/product")
@Tag(name = "Product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        try {
            List<ProductDTO> list = this.service.findAll();
            return ResponseEntity.ok(list);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Object> findById(@PathVariable(name = "id") UUID id) {
        try {
            ProductDTO obj = this.service.findById(id);
            return ResponseEntity.ok(obj);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ProductDTO dto) {
        try {
            this.service.insert(dto);
            return ResponseEntity.ok("Registro incluído com sucesso!");
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody ProductDTO dto) {
        try {
            this.service.insert(dto);
            return ResponseEntity.ok("Registro incluído com sucesso!");
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }
}

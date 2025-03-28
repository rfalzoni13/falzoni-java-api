package br.com.falzoni.falzoni_java_api.controllers.register;

import br.com.falzoni.falzoni_java_api.domain.dtos.classes.register.CustomerDTO;
import br.com.falzoni.falzoni_java_api.domain.dtos.classes.security.ResponseDTO;
import br.com.falzoni.falzoni_java_api.services.interfaces.register.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Listar todos os clientes", description = "Obtém todos os registros de clientes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CustomerDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        try {
            List<CustomerDTO> list = this.service.findAll();
            return ResponseEntity.ok(list);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @Operation(summary = "Listar cliente por id", description = "Obtém o cliente através do id passado via parâmetro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/findById/{id}")
    public ResponseEntity<Object> findById(@PathVariable(name = "id") UUID id) {
        try {
            CustomerDTO obj = this.service.findById(id);
            return ResponseEntity.ok(obj);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @Operation(summary = "Inserir cliente", description = "Cria um novo registro de cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody CustomerDTO dto) {
        try {
            this.service.insert(dto);
            return ResponseEntity.ok("Registro incluído com sucesso!");
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @Operation(summary = "Atualizar cliente", description = "Atualiza um registro de cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody CustomerDTO dto) {
        try {
            this.service.update(dto);
            return ResponseEntity.ok("Registro incluído com sucesso!");
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @Operation(summary = "Excluir cliente", description = "Deleta o cliente através do id passado via parâmetro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable(name = "id") UUID id) {
        try {
            this.service.delete(id);
            return ResponseEntity.ok("Registro removido com sucesso!");
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }
}

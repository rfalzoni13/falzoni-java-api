package br.com.falzoni.falzoni_java_api.controllers.stock;

import br.com.falzoni.falzoni_java_api.domain.dtos.classes.security.ResponseDTO;
import br.com.falzoni.falzoni_java_api.domain.dtos.classes.stock.ProductDTO;
import br.com.falzoni.falzoni_java_api.services.interfaces.stock.ProductService;
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
@RequestMapping("aoi/product")
@Tag(name = "Product")
public class ProductController {
    @Autowired
    private ProductService service;

    @Operation(summary = "Listar todos os produtos", description = "Obtém todos os registros de produtos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ProductDTO.class)))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/findAll")
    public ResponseEntity<Object> findAll() {
        try {
            List<ProductDTO> list = this.service.findAll();
            return ResponseEntity.ok(list);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @Operation(summary = "Listar produto por id", description = "Obtém o produto através do id passado via parâmetro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @GetMapping("/findById/{id}")
    public ResponseEntity<Object> findById(@PathVariable(name = "id") UUID id) {
        try {
            ProductDTO obj = this.service.findById(id);
            return ResponseEntity.ok(obj);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @Operation(summary = "Inserir produto", description = "Cria um novo registro de produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody ProductDTO dto) {
        try {
            this.service.insert(dto);
            return ResponseEntity.ok("Registro incluído com sucesso!");
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @Operation(summary = "Atualizar produto", description = "Atualiza um registro de produto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
    })
    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody ProductDTO dto) {
        try {
            this.service.insert(dto);
            return ResponseEntity.ok("Registro incluído com sucesso!");
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @Operation(summary = "Excluir produto", description = "Deleta o produto através do id passado via parâmetro")
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

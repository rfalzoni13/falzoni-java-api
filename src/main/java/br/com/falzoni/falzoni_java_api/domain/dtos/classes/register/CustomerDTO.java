package br.com.falzoni.falzoni_java_api.domain.dtos.classes.register;

import br.com.falzoni.falzoni_java_api.domain.dtos.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "Customer", type = "object", description = "Objeto do cliente")
public class CustomerDTO extends BaseDTO {

    @Schema(description = "Nome do cliente", type = "string", example = "Test Customer")
    private String name;

    @Schema(description = "Documento do cliente", type = "string", example = "123.456.789-00")
    private String document;

    public CustomerDTO(UUID id, String name, String document) {
        this.id = id;
        this.name = name;
        this.document = document;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}

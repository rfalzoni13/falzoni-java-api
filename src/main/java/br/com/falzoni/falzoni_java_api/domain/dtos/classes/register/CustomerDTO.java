package br.com.falzoni.falzoni_java_api.domain.dtos.classes.register;

import br.com.falzoni.falzoni_java_api.domain.dtos.base.BaseDTO;

import java.util.UUID;

public class CustomerDTO extends BaseDTO {
    private String name;
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

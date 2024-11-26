package br.com.falzoni.falzoni_java_api.domain.entities.register;

import br.com.falzoni.falzoni_java_api.domain.entities.base.BaseEntity;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class Customer extends BaseEntity {
    @Column(length = 128, nullable = false)
    private String name;

    @Column(length = 20, nullable = false)
    private String document;

    public Customer() {
    }

    public Customer(String name, String document) {
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

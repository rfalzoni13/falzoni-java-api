package br.com.falzoni.falzoni_java_api.domain.dtos.classes.stock;

import br.com.falzoni.falzoni_java_api.domain.dtos.base.BaseDTO;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "Product", type = "object", description = "Objeto do produto")
public class ProductDTO extends BaseDTO {

    @Schema(description = "Nome do produto", type = "string", example = "Notebook Gamer")
    private String name;

    @Schema(description = "Preço do produto", type = "number", format = "decimal", example = "100.00")
    private double price;

    @Schema(description = "Valor do desconto", type = "number", format = "decimal", example = "5.00")
    private double discount;

    public ProductDTO(UUID id, String name, double price, double discount) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discount = discount;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}

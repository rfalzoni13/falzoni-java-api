package br.com.falzoni.falzoni_java_api.controllers.stock;

import br.com.falzoni.falzoni_java_api.domain.entities.stock.Product;
import br.com.falzoni.falzoni_java_api.services.stock.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("aoi/product")
@Tag(name = "Product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/findAll")
    public List<Product> findAll() {
        return service.findAll();
    }
}

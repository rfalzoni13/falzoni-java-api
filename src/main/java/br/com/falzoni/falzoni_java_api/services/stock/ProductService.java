package br.com.falzoni.falzoni_java_api.services.stock;

import br.com.falzoni.falzoni_java_api.domain.dtos.classes.stock.ProductDTO;
import br.com.falzoni.falzoni_java_api.domain.entities.register.Customer;
import br.com.falzoni.falzoni_java_api.domain.entities.stock.Product;
import br.com.falzoni.falzoni_java_api.repositories.stock.ProductRepository;
import br.com.falzoni.falzoni_java_api.services.base.AbstractService;
import br.com.falzoni.falzoni_java_api.utils.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService extends AbstractService<ProductDTO> {
    @Autowired
    private ProductRepository repository;


    @Override
    public List<ProductDTO> findAll() {
        List<Product> list = repository.findAll();
        return list.stream()
                .map(p -> new ProductDTO(p.getId(), p.getName(), p.getPrice(), p.getDiscount()))
                .toList();
    }

    @Override
    public ProductDTO findById(UUID id) {
        Product obj = repository.findById(id).orElse(null);
        if(obj != null) {
            return new ProductDTO(obj.getId(), obj.getName(), obj.getPrice(), obj.getDiscount());
        }
        return null;
    }

    @Override
    public void insert(ProductDTO obj) {
        try {
            Validate(obj);
            Product product = new Product(obj.getName(), obj.getPrice(), obj.getDiscount());
            repository.save(product);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void update(ProductDTO obj) {
        try {
            Validate(obj);
            Optional<Product> hasProduct = repository.findById(obj.getId());
            if(hasProduct.isPresent()) {
                Product product = hasProduct.get();
                product.setName(obj.getName());
                product.setPrice(obj.getPrice());
                product.setDiscount(obj.getDiscount());
                repository.save(product);
            } else {
                throw new Exception("Registro não encontrado");
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void delete(UUID id) {
        try {
            Optional<Product> optional = repository.findById(id);
            if(optional.isEmpty()) throw new Exception("Registro não encontrado");

            Product product = optional.get();
            repository.delete(product);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    // private METHODS
    private void Validate(ProductDTO obj) throws Exception {
        if (ValidationUtils.CheckNullValue(obj.getName()))
            throw new Exception("O nome do produto não foi preenchido corretamente");

        if (obj.getPrice() <= 0)
            throw new Exception("O preço do produto não foi preenchido corretamente");
    }
}

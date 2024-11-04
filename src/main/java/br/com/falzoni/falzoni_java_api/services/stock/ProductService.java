package br.com.falzoni.falzoni_java_api.services.stock;

import br.com.falzoni.falzoni_java_api.domain.entities.stock.Product;
import br.com.falzoni.falzoni_java_api.repositories.stock.ProductRepository;
import br.com.falzoni.falzoni_java_api.services.base.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService extends AbstractService<Product> {
    @Autowired
    private ProductRepository repository;


    @Override
    public List<Product> findAll() {
        return repository.findAll();
    }

    @Override
    public Product findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void insert(Product obj) {

    }

    @Override
    public void update(Product obj) {
        Optional<Product> hasProduct = repository.findById(obj.getId());
        if(hasProduct.isPresent()) {
            Product product = hasProduct.get();
            product.setName(obj.getName());
            product.setPrice(obj.getPrice());
            product.setDiscount(obj.getDiscount());
            repository.save(product);
        }
    }

    @Override
    public void delete(Product obj) {
        repository.delete(obj);
    }
}

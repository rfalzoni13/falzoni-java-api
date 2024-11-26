package br.com.falzoni.falzoni_java_api.repositories.stock;

import br.com.falzoni.falzoni_java_api.domain.entities.register.Customer;
import br.com.falzoni.falzoni_java_api.domain.entities.stock.Product;
import br.com.falzoni.falzoni_java_api.repositories.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
public class ProductRepositoryTest implements RepositoryTest {
    @Autowired
    private ProductRepository repository;

    @Override
    @Test
    @DisplayName("Test for create product operation")
    public void create_test() {
        Product product = this.repository.save(new Product("Tênis Alpha", 89.99, 10.00));

        assertThat(product).isNotNull();
        assertThat(product.getId()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for update product operation")
    public void update_test() {
        try {
            Product product = setDataToProductUpdate(UUID.fromString("6e5fa41e-5e6a-44d1-b9a4-ee42e389d40f"));

            product = this.repository.save(product);

            assertThat(product).isNotNull();
            assertThat(product.getId()).isNotNull();
            assertThat(product.getName()).isEqualTo("Garfo prateado");
            assertThat(product.getPrice()).isEqualTo(10.00);
            assertThat(product.getDiscount()).isEqualTo(5.00);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    @Test
    @DisplayName("Test for delete product operation")
    public void delete_test() {
        Product product = new Product("Garfo prateado", 10.0, 5.00);
        product.setId(UUID.fromString("6e5fa41e-5e6a-44d1-b9a4-ee42e389d40f"));

        this.repository.delete(product);

        assertThat(product).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for find product operation")
    public void find_test() {
        Optional<Product> product = this.repository.findById(UUID.fromString("0bad80f4-ba82-42bb-bd9c-6f5230f9e835"));

        assertThat(product.isPresent()).isTrue();
        assertThat(product.get()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for find all products operation")
    public void find_all_test() {
        List<Product> list = this.repository.findAll();

        assertThat(list).isNotEmpty();
        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(3);
    }

    // private METHODS
    private Product setDataToProductUpdate(UUID uuid) throws Exception {
        Optional<Product> optional = this.repository.findById(uuid);

        if (optional.isEmpty()) throw new Exception("Nenhum registro encontrado");

        Product product = optional.get();

        product.setName("Garfo prateado");
        product.setPrice(10.00);
        product.setDiscount(5.00);

        return product;
    }
}

package br.com.falzoni.falzoni_java_api.services.stock;

import br.com.falzoni.falzoni_java_api.domain.entities.stock.Product;
import br.com.falzoni.falzoni_java_api.services.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
public class ProductServiceImplTest implements ServiceTest {
    @Autowired
    private ProductService service;

    @Override
    @Test
    @DisplayName("Test for create product service operation")
    public void create_test_success() {
        Product obj = new Product("Caneta Bic", 19.99, 4.5);
        service.insert(obj);
        assertThat(obj.getId()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for create product service failure")
    public void create_test_failure() {
        Product obj = new Product(null, 0, 0);
        assertThrows(RuntimeException.class, () -> service.insert(obj));
    }

    @Override
    @Test
    @DisplayName("Test for update product service operation")
    public void update_test_success() {
        Product obj = new Product("Notebook Gamer", 4999.99, 0);
        obj.setId(UUID.fromString("84804497-6f87-46ec-8b97-c9ddaae5f4fd"));
        service.update(obj);
        assertThat(obj.getId()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for update product service failure")
    public void update_test_failure() {
        Product obj = new Product(null, 0, 0);
        obj.setId(UUID.fromString("6e5fa41e-5e6a-44d1-b9a4-ee42e389d40f"));
        assertThrows(RuntimeException.class, () -> service.update(obj), "O nome do produto não foi preenchido corretamente");
    }

    @Override
    @Test
    @DisplayName("Test for update product service not found")
    public void update_test_not_found() {
        Product obj = new Product("Vídeo Game PolyStation", 399.99, 5.00);
        assertThrows(RuntimeException.class, () -> service.update(obj), "Registro não encontrado");
    }

    @Override
    @Test
    @DisplayName("Test for find product service success")
    public void find_test_data() {
        Product obj = service.findById(UUID.fromString("84804497-6f87-46ec-8b97-c9ddaae5f4fd"));

        assertThat(obj).isNotNull();
        assertThat(obj.getId()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for find product service empty")
    public void find_test_empty() {
        Product obj = service.findById(UUID.fromString("65462132-ce24-4311-98d7-654613213544"));

        assertThat(obj).isNull();
    }

    @Override
    @Test
    @DisplayName("Test for find all products service success")
    public void find_all_test_success() {
        List<Product> list = service.findAll();

        assertThat(list).isNotNull();
        assertThat(list).isNotEmpty();
        assertThat(list.size()).isEqualTo(3);
    }
}

package br.com.falzoni.falzoni_java_api.services.stock;

import br.com.falzoni.falzoni_java_api.domain.dtos.classes.stock.ProductDTO;
import br.com.falzoni.falzoni_java_api.services.ServiceTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
public class ProductServiceTest implements ServiceTest {
    @Autowired
    private ProductService service;

    @Override
    @Test
    @DisplayName("Test for create product service operation")
    public void create_test_success() {
        ProductDTO obj = new ProductDTO(null, "Caneta Bic", 19.99, 4.5);
        assertDoesNotThrow(() -> service.insert(obj));
    }

    @Override
    @Test
    @DisplayName("Test for create product service failure")
    public void create_test_failure() {
        ProductDTO obj = new ProductDTO(null, null, 0, 0);
        assertThrows(RuntimeException.class, () -> service.insert(obj));
    }

    @Override
    @Test
    @DisplayName("Test for update product service operation")
    public void update_test_success() {
        ProductDTO obj = new ProductDTO(UUID.fromString("84804497-6f87-46ec-8b97-c9ddaae5f4fd"), "Notebook Gamer", 4999.99, 0);
        assertDoesNotThrow(() -> service.update(obj));
    }

    @Override
    @Test
    @DisplayName("Test for update product service failure")
    public void update_test_failure() {
        ProductDTO obj = new ProductDTO(UUID.fromString("6e5fa41e-5e6a-44d1-b9a4-ee42e389d40f"), null, 0, 0);
        assertThrows(RuntimeException.class, () -> service.update(obj), "O nome do produto não foi preenchido corretamente");
    }

    @Override
    @Test
    @DisplayName("Test for update product service not found")
    public void update_test_not_found() {
        ProductDTO obj = new ProductDTO(null, "Vídeo Game PolyStation", 399.99, 5.00);
        assertThrows(RuntimeException.class, () -> service.update(obj), "Registro não encontrado");
    }

    @Test
    @DisplayName("Test for delete product service operation")
    @Override
    public void delete_test_success() {
        UUID id = UUID.fromString("0bad80f4-ba82-42bb-bd9c-6f5230f9e835");
        assertDoesNotThrow(() -> service.delete(id));
    }

    @Test
    @DisplayName("Test for delete product service not found")
    @Override
    public void delete_test_not_found() {
        UUID id = UUID.fromString("65462132-ce24-4311-98d7-c7495e093158");
        assertThrows(RuntimeException.class, () -> service.delete(id), "Registro não encontrado");
    }

    @Override
    @Test
    @DisplayName("Test for find product service success")
    public void find_test_data() {
        ProductDTO obj = service.findById(UUID.fromString("84804497-6f87-46ec-8b97-c9ddaae5f4fd"));

        assertThat(obj).isNotNull();
        assertThat(obj.getId()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for find product service empty")
    public void find_test_empty() {
        ProductDTO obj = service.findById(UUID.fromString("65462132-ce24-4311-98d7-654613213544"));

        assertThat(obj).isNull();
    }

    @Override
    @Test
    @DisplayName("Test for find all products service success")
    public void find_all_test_success() {
        List<ProductDTO> list = service.findAll();

        assertThat(list).isNotNull();
        assertThat(list).isNotEmpty();
        assertThat(list.size()).isEqualTo(2);
    }
}

package br.com.falzoni.falzoni_java_api.services.stock;

import br.com.falzoni.falzoni_java_api.domain.dtos.classes.stock.ProductDTO;
import br.com.falzoni.falzoni_java_api.domain.entities.stock.Product;
import br.com.falzoni.falzoni_java_api.repositories.stock.ProductRepository;
import br.com.falzoni.falzoni_java_api.services.ServiceTest;
import br.com.falzoni.falzoni_java_api.services.classes.stock.ProductServiceImpl;
import br.com.falzoni.falzoni_java_api.services.interfaces.stock.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;

@ActiveProfiles("test")
@SpringBootTest
public class ProductServiceTest implements ServiceTest {
    @Mock
    private ProductRepository repository;

    @Autowired
    @InjectMocks
    private ProductService service = new ProductServiceImpl();

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Override
    @Test
    @DisplayName("Test for create product service operation")
    public void create_test_success() {
        ProductDTO obj = new ProductDTO(null, "Caneta Bic", 19.99, 4.5);
        assertDoesNotThrow(() -> this.service.insert(obj));
    }

    @Override
    @Test
    @DisplayName("Test for create product service failure")
    public void create_test_failure() {
        ProductDTO obj = new ProductDTO(null, null, 0, 0);
        assertThrows(RuntimeException.class, () -> this.service.insert(obj));
    }

    @Override
    @Test
    @DisplayName("Test for update product service operation")
    public void update_test_success() {
        UUID id = UUID.fromString("84804497-6f87-46ec-8b97-c9ddaae5f4fd");

        Product product = new Product("Notebook Gamer", 4999.99, 0);
        product.setId(id);

        doReturn(Optional.of(product))
                .when(this.repository)
                .findById(id);

        ProductDTO obj = new ProductDTO(id, product.getName(), product.getPrice(), product.getDiscount());

        assertDoesNotThrow(() -> this.service.update(obj));
    }

    @Override
    @Test
    @DisplayName("Test for update product service failure")
    public void update_test_failure() {
        ProductDTO obj = new ProductDTO(UUID.fromString("6e5fa41e-5e6a-44d1-b9a4-ee42e389d40f"), null, 0, 0);
        assertThrows(RuntimeException.class, () -> this.service.update(obj), "O nome do produto não foi preenchido corretamente");
    }

    @Override
    @Test
    @DisplayName("Test for update product service not found")
    public void update_test_not_found() {
        UUID id = UUID.fromString("65462132-ce24-4311-98d7-c7495e093158");

        doReturn(Optional.empty())
                .when(this.repository)
                .findById(id);

        ProductDTO obj = new ProductDTO(id, "Vídeo Game PolyStation", 399.99, 5.00);
        assertThrows(RuntimeException.class, () -> this.service.update(obj), "Registro não encontrado");
    }

    @Test
    @DisplayName("Test for delete product service operation")
    @Override
    public void delete_test_success() {
        UUID id = UUID.fromString("0bad80f4-ba82-42bb-bd9c-6f5230f9e835");

        Product product = new Product("Mochila Saint Seiya", 29.99, 0.00);
        product.setId(id);

        doReturn(Optional.of(product))
                .when(this.repository)
                .findById(id);

        assertDoesNotThrow(() -> this.service.delete(id));
    }

    @Test
    @DisplayName("Test for delete product service not found")
    @Override
    public void delete_test_not_found() {
        UUID id = UUID.fromString("65462132-ce24-4311-98d7-c7495e093158");

        doReturn(Optional.empty())
                .when(this.repository)
                .findById(id);

        assertThrows(RuntimeException.class, () -> this.service.delete(id), "Registro não encontrado");
    }

    @Override
    @Test
    @DisplayName("Test for find product service success")
    public void find_test_data() {
        UUID id = UUID.fromString("84804497-6f87-46ec-8b97-c9ddaae5f4fd");

        Product product = new Product("Notebook Gamer", 4999.99, 0.00);
        product.setId(id);

        doReturn(Optional.of(product))
                .when(this.repository)
                .findById(id);

        ProductDTO obj = this.service.findById(id);

        assertThat(obj).isNotNull();
        assertThat(obj.getId()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for find product service empty")
    public void find_test_empty() {
        UUID id = UUID.fromString("65462132-ce24-4311-98d7-654613213544");


        doReturn(Optional.empty())
                .when(this.repository)
                .findById(id);

        ProductDTO obj = this.service.findById(id);

        assertThat(obj).isNull();
    }

    @Override
    @Test
    @DisplayName("Test for find all products service success")
    public void find_all_test_success() {
        List<Product> products = getList().stream().toList();

        doReturn(products)
                .when(this.repository)
                .findAll();

        List<ProductDTO> list = this.service.findAll();

        assertThat(list).isNotNull();
        assertThat(list).isNotEmpty();
        assertThat(list.size()).isEqualTo(3);
    }

    // private METHODS
    private HashSet<Product> getList() {
        HashSet<Product> products = new HashSet<>();

        Product p1 = new Product("Notebook Gamer", 4999.99, 0.00);
        p1.setId(UUID.fromString("84804497-6f87-46ec-8b97-c9ddaae5f4fd"));
        products.add(p1);

        Product p2 = new Product("Vídeo Game PolyStation", 399.99, 5.00);
        p2.setId(UUID.fromString("6e5fa41e-5e6a-44d1-b9a4-ee42e389d40f"));
        products.add(p2);

        Product p3 = new Product("Mochila Saint Seiya", 29.99, 0.00);
        p3.setId(UUID.fromString("0bad80f4-ba82-42bb-bd9c-6f5230f9e835"));
        products.add(p3);

        return products;
    }
}

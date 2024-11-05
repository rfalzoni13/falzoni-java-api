package br.com.falzoni.falzoni_java_api.services.entries;

import br.com.falzoni.falzoni_java_api.domain.entities.entries.Customer;
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
public class CustomerServiceImplTest implements ServiceTest {
    @Autowired
    private CustomerService service;

    @Override
    @Test
    @DisplayName("Test for create customer service operation")
    public void create_test_success() {
        Customer obj = new Customer("Bruce Dickinson", "321.321.456-00");
        service.insert(obj);
        assertThat(obj.getId()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for create customer service failure")
    public void create_test_failure() {
        Customer obj = new Customer(null, "321.321.456-00");
        assertThrows(RuntimeException.class, () -> service.insert(obj));
    }

    @Override
    @Test
    @DisplayName("Test for update customer service operation")
    public void update_test_success() {
        Customer obj = new Customer("Simas Turbo", "321.456.878-32");
        obj.setId(UUID.fromString("8d4de05b-0a8a-4e5b-809f-1ce392a4352a"));
        service.update(obj);
        assertThat(obj.getId()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for update customer service failure")
    public void update_test_failure() {
        Customer obj = new Customer("Simas Turbo", null);
        obj.setId(UUID.fromString("8d4de05b-0a8a-4e5b-809f-1ce392a4352a"));
        assertThrows(RuntimeException.class, () -> service.update(obj), "O documento do cliente não foi preenchido corretamente");
    }

    @Override
    @Test
    @DisplayName("Test for update customer service not found")
    public void update_test_not_found() {
        Customer obj = new Customer("Simas Turbo", "321.456.878-32");
        assertThrows(RuntimeException.class, () -> service.update(obj), "Registro não encontrado");
    }

    @Override
    @Test
    @DisplayName("Test for find customer service success")
    public void find_test_data() {
        Customer obj = service.findById(UUID.fromString("6837626f-ce24-4311-98d7-c7495e093158"));

        assertThat(obj).isNotNull();
        assertThat(obj.getId()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for find customer service empty")
    public void find_test_empty() {
        Customer obj = service.findById(UUID.fromString("65462132-ce24-4311-98d7-c7495e093158"));

        assertThat(obj).isNull();
    }

    @Override
    @Test
    @DisplayName("Test for find all customers service success")
    public void find_all_test_success() {
        List<Customer> list = service.findAll();

        assertThat(list).isNotNull();
        assertThat(list).isNotEmpty();
        assertThat(list.size()).isEqualTo(3);
    }
}

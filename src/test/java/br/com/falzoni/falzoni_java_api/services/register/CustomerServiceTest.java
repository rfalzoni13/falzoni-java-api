package br.com.falzoni.falzoni_java_api.services.register;

import br.com.falzoni.falzoni_java_api.domain.dtos.classes.register.CustomerDTO;
import br.com.falzoni.falzoni_java_api.domain.entities.register.Customer;
import br.com.falzoni.falzoni_java_api.repositories.register.CustomerRepository;
import br.com.falzoni.falzoni_java_api.services.ServiceTest;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@ActiveProfiles("test")
@SpringBootTest
public class CustomerServiceTest implements ServiceTest {
    @Mock
    private CustomerRepository repository;

    @Autowired
    @InjectMocks
    private CustomerService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Override
    @Test
    @DisplayName("Test for create customer service operation")
    public void create_test_success() {
        CustomerDTO obj = new CustomerDTO(null, "Bruce Dickinson", "321.321.456-00");
        assertDoesNotThrow(() -> this.service.insert(obj));
    }

    @Override
    @Test
    @DisplayName("Test for create customer service failure")
    public void create_test_failure() {
        CustomerDTO obj = new CustomerDTO(null, null, "321.321.456-00");
        assertThrows(RuntimeException.class, () -> this.service.insert(obj));
    }

    @Override
    @Test
    @DisplayName("Test for update customer service operation")
    public void update_test_success() {
        UUID id = UUID.fromString("8d4de05b-0a8a-4e5b-809f-1ce392a4352a");

        Customer customer = new Customer("Simas Turbo", "321.456.878-32");
        customer.setId(id);

        doReturn(Optional.of(customer))
                .when(this.repository)
                .findById(id);

        CustomerDTO dto = new CustomerDTO(id, customer.getName(), customer.getDocument());

        assertDoesNotThrow(() -> this.service.update(dto));
    }

    @Override
    @Test
    @DisplayName("Test for update customer service failure")
    public void update_test_failure() {
        CustomerDTO obj = new CustomerDTO(UUID.fromString("8d4de05b-0a8a-4e5b-809f-1ce392a4352a"), "Simas Turbo", null);
        assertThrows(RuntimeException.class, () -> this.service.update(obj), "O documento do cliente não foi preenchido corretamente");
    }

    @Override
    @Test
    @DisplayName("Test for update customer service not found")
    public void update_test_not_found() {
        UUID id = UUID.fromString("65462132-ce24-4311-98d7-c7495e093158");

        doReturn(Optional.empty())
                .when(this.repository)
                .findById(id);

        CustomerDTO obj = new CustomerDTO(id, "Simas Turbo", "321.456.878-32");
        assertThrows(RuntimeException.class, () -> this.service.update(obj), "Registro não encontrado");
    }

    @Test
    @DisplayName("Test for delete customer service operation")
    @Override
    public void delete_test_success() {
        UUID id = UUID.fromString("a8b7b18e-5e78-4fa6-970a-2c4ebfb74b4d");

        Customer customer = new Customer("Naruto Uzumaki", "325.854.320-88");
        customer.setId(id);

        doReturn(Optional.of(customer))
                .when(this.repository)
                .findById(id);

        assertDoesNotThrow(() -> this.service.delete(id));
    }

    @Test
    @DisplayName("Test for delete customer service not found")
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
    @DisplayName("Test for find customer service success")
    public void find_test_data() {
        UUID id = UUID.fromString("6837626f-ce24-4311-98d7-c7495e093158");

        Customer customer = new Customer("Light Yagami", "402.321.898-18");
        customer.setId(id);

        doReturn(Optional.of(customer))
                .when(this.repository)
                .findById(id);

        CustomerDTO obj = this.service.findById(id);

        assertThat(obj).isNotNull();
        assertThat(obj.getId()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for find customer service empty")
    public void find_test_empty() {
        UUID id = UUID.fromString("65462132-ce24-4311-98d7-c7495e093158");

        doReturn(Optional.empty())
                .when(this.repository)
                .findById(id);

        CustomerDTO obj = this.service.findById(UUID.fromString("65462132-ce24-4311-98d7-c7495e093158"));

        assertThat(obj).isNull();
    }

    @Override
    @Test
    @DisplayName("Test for find all customers service success")
    public void find_all_test_success() {
        List<Customer> customers = getList().stream().toList();

        doReturn(customers)
                .when(this.repository)
                .findAll();

        List<CustomerDTO> list = this.service.findAll();

        assertThat(list).isNotNull();
        assertThat(list).isNotEmpty();
        assertThat(list.size()).isEqualTo(3);
    }

    // private METHODS
    private HashSet<Customer> getList() {
        HashSet<Customer> customers = new HashSet<>();

        Customer c1 = new Customer("Simas Turbo", "321.456.878-32");
        c1.setId(UUID.fromString("8d4de05b-0a8a-4e5b-809f-1ce392a4352a"));
        customers.add(c1);

        Customer c2 = new Customer("Naruto Uzumaki", "325.854.320-88");
        c2.setId(UUID.fromString("a8b7b18e-5e78-4fa6-970a-2c4ebfb74b4d"));
        customers.add(c2);

        Customer c3 = new Customer("Light Yagami", "402.321.898-18");
        c3.setId(UUID.fromString("6837626f-ce24-4311-98d7-c7495e093158"));
        customers.add(c3);

        return customers;
    }
}

package br.com.falzoni.falzoni_java_api.repositories.register;

import br.com.falzoni.falzoni_java_api.domain.entities.register.Customer;
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
public class CustomerRepositoryTest implements RepositoryTest {
    @Autowired
    private CustomerRepository repository;

    @Override
    @Test
    @DisplayName("Test for create customer operation")
    public void create_test() {
        Customer customer = this.repository.save(new Customer("Seiya de Pégaso", "123.456.789-00"));

        assertThat(customer).isNotNull();
        assertThat(customer.getId()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for update customer operation")
    public void update_test() {
        try {
            Customer customer = setDataToCustomerUpdate(UUID.fromString("6837626f-ce24-4311-98d7-c7495e093158"));

            customer = this.repository.save(customer);

            assertThat(customer).isNotNull();
            assertThat(customer.getId()).isNotNull();
            assertThat(customer.getName()).isEqualTo("Shiryu de Dragão");
            assertThat(customer.getDocument()).isEqualTo("36.698.312-00");
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    @Test
    @DisplayName("Test for delete customer operation")
    public void delete_test() {
        Customer customer = new Customer("Shiryu de Dragão", "36.698.312-00");
        customer.setId(UUID.fromString("6837626f-ce24-4311-98d7-c7495e093158"));

        this.repository.delete(customer);

        assertThat(customer).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for find customer operation")
    public void find_test() {
        Optional<Customer> customer = repository.findById(UUID.fromString("8d4de05b-0a8a-4e5b-809f-1ce392a4352a"));

        assertThat(customer.isPresent()).isTrue();
        assertThat(customer.get()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for find all customers operation")
    public void find_all_test() {
        List<Customer> list = repository.findAll();

        assertThat(list).isNotEmpty();
        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(3);
    }

    // private METHODS
    private Customer setDataToCustomerUpdate(UUID uuid) throws Exception {
        Optional<Customer> optional = repository.findById(uuid);

        if(optional.isEmpty()) throw new Exception("Nenhum registro encontrado");

        Customer customer = optional.get();

        customer.setName("Shiryu de Dragão");
        customer.setDocument("36.698.312-00");

        return customer;
    }
}

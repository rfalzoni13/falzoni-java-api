package br.com.falzoni.falzoni_java_api.repositories.register;

import br.com.falzoni.falzoni_java_api.domain.entities.register.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}

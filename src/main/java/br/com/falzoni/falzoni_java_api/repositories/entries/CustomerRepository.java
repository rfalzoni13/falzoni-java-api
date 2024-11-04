package br.com.falzoni.falzoni_java_api.repositories.entries;

import br.com.falzoni.falzoni_java_api.domain.entities.entries.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {
}

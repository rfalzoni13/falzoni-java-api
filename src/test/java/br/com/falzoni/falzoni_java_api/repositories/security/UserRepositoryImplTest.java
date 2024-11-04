package br.com.falzoni.falzoni_java_api.repositories.security;

import br.com.falzoni.falzoni_java_api.domain.entities.security.User;
import br.com.falzoni.falzoni_java_api.domain.enums.UserRole;
import br.com.falzoni.falzoni_java_api.repositories.RepositoryTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
public class UserRepositoryImplTest implements RepositoryTest {
    @Autowired
    private UserRepository repository;

    @Override
    @Test
    @DisplayName("Test for create user operation")
    public void create_test() {
        User user = repository.save(
                new User(
                        "Maria do Bairro",
                        "mariadb",
                        "321654",
                        "maria.db@hotmail.com",
                        "(11) 96847-4545", UserRole.USER));

        assertThat(user).isNotNull();
        assertThat(user.getId()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for update user operation")
    public void update_test() {
        try {
            User user = setDataToUserUpdate(UUID.fromString("55d18bd8-05f2-4410-8c63-14ab6b7ac06e"));

            user = repository.save(user);

            assertThat(user).isNotNull();
            assertThat(user.getId()).isNotNull();
            assertThat(user.getUserName()).isEqualTo("hcisne");
            assertThat(user.getFullName()).isEqualTo("Hyoga de Cisne");
            assertThat(user.getEmail()).isEqualTo("hyogadecisne.bronze@gmail.com");
            assertThat(BCrypt.checkpw("654321",user.getPassword())).isTrue();
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    @Test
    @DisplayName("Test for find user operation")
    public void find_test() {
        Optional<User> user = repository.findById(UUID.fromString("436f13b6-bf4f-45f7-8bb8-4c904fecd799"));

        assertThat(user.isPresent()).isTrue();
        assertThat(user.get()).isNotNull();
    }

    @Override
    @Test
    @DisplayName("Test for find all users operation")
    public void find_all_test() {
        List<User> list = repository.findAll();

        assertThat(list).isNotEmpty();
        assertThat(list).isNotNull();
        assertThat(list.size()).isEqualTo(3);
    }

    // private METHODS
    private User setDataToUserUpdate(UUID uuid) throws Exception {
        Optional<User> optional = repository.findById(uuid);

        if (optional.isEmpty()) throw new Exception("Nenhum registro encontrado");

        User user = optional.get();

        user.setUserName("hcisne");
        user.setFullName("Hyoga de Cisne");
        user.setEmail("hyogadecisne.bronze@gmail.com");
        user.setPassword(BCrypt.hashpw("654321", BCrypt.gensalt(10)));
        user.setPhoneNumber("(11) 92222-2222");

        return user;
    }
}

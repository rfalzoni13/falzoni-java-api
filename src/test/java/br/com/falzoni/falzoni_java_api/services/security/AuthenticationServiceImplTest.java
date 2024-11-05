package br.com.falzoni.falzoni_java_api.services.security;

import br.com.falzoni.falzoni_java_api.domain.dtos.classes.TokenResponseDTO;
import br.com.falzoni.falzoni_java_api.domain.dtos.records.LoginDTO;
import br.com.falzoni.falzoni_java_api.domain.dtos.records.RegisterDTO;
import br.com.falzoni.falzoni_java_api.domain.enums.UserRole;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ActiveProfiles("test")
@SpringBootTest
public class AuthenticationServiceImplTest {
    @Autowired
    private AuthenticationService service;

    @Test
    @DisplayName("Test for authenticate service operation success")
    public void authenticate_return_success() {
        LoginDTO loginDTO = new LoginDTO("luffyonep", "123456");
        TokenResponseDTO response = service.authenticate(loginDTO);

        assertThat(response).isNotNull();
        assertThat(response.getToken()).isNotEmpty();
        assertThat(response.getExpiresAt()).isNotNull();
        assertThat(response.getIssuedAt()).isNotNull();
    }

    @Test
    @DisplayName("Test for authenticate service operation user bad credentials")
    public void authenticate_return_bad_credentials() {
        LoginDTO loginDTO = new LoginDTO("luffyonep", "654321");
        assertThrows(BadCredentialsException.class, () -> service.authenticate(loginDTO), "Usuário inexistente ou senha inválida");
    }

    @Test
    @DisplayName("Test for register service operation success")
    public void register_return_success() throws Exception {
        RegisterDTO dto = new RegisterDTO("Chun Li", "chun_li", "654321", "chunli.streetfighter@hotmail.com", "(11) 2222-2222", UserRole.USER);
        service.register(dto);
        assertThat(dto).isNotNull();
    }

    @Test
    @DisplayName("Test for register service operation failure")
    public void register_return_failure() throws Exception {
        RegisterDTO dto = new RegisterDTO(null, "chun_li", "654321", "chunli.streetfighter@hotmail.com", "(11) 2222-2222", UserRole.USER);
        assertThrows(Exception.class, () -> service.register(dto));
    }

    @Test
    @DisplayName("Test for register service operation exists user")
    public void register_return_exists_user() throws Exception {
        RegisterDTO dto = new RegisterDTO("Luffy do Chapéu de Palha", "luffyonep", "123456", "luffy.palha@gmail.com", "(11) 2222-2222", UserRole.ADMIN);
        assertThrows(Exception.class, () -> service.register(dto), "Usuário já existe!");
    }
}

package br.com.falzoni.falzoni_java_api.services.security;

import br.com.falzoni.falzoni_java_api.configuration.security.JwtConfig;
import br.com.falzoni.falzoni_java_api.domain.dtos.classes.security.TokenResponseDTO;
import br.com.falzoni.falzoni_java_api.domain.dtos.records.LoginDTO;
import br.com.falzoni.falzoni_java_api.domain.dtos.records.RegisterDTO;
import br.com.falzoni.falzoni_java_api.domain.entities.security.User;
import br.com.falzoni.falzoni_java_api.repositories.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private JwtConfig jtwConfig;

    @Autowired
    private AuthenticationManager authenticationManager;

    public TokenResponseDTO authenticate(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken userNamePassword
                = new UsernamePasswordAuthenticationToken(loginDTO.userName(), loginDTO.password());
        Authentication authentication = this.authenticationManager.authenticate(userNamePassword);
        return jtwConfig.generateToken(authentication);
    }

    public void register(RegisterDTO dto) throws Exception {
        if (repository.findByUserName(dto.userName()).isPresent()) throw new Exception("Usuário já existe!");

        String encryptPassword = new BCryptPasswordEncoder().encode(dto.password());
        User user = new User(
                dto.fullName(),
                dto.userName(),
                encryptPassword,
                dto.email(),
                dto.phoneNumber(),
                dto.role()
        );

        repository.save(user);
    }
}

package br.com.falzoni.falzoni_java_api.controllers.security;

import br.com.falzoni.falzoni_java_api.domain.dtos.classes.security.ResponseDTO;
import br.com.falzoni.falzoni_java_api.domain.dtos.classes.security.TokenResponseDTO;
import br.com.falzoni.falzoni_java_api.domain.dtos.records.LoginDTO;
import br.com.falzoni.falzoni_java_api.domain.dtos.records.RegisterDTO;
import br.com.falzoni.falzoni_java_api.services.classes.security.AuthenticationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/authentication")
@Tag(name = "Authentication")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            TokenResponseDTO token = this.authenticationService.authenticate(loginDTO);
            return ResponseEntity.ok().body(token);
        } catch (AuthenticationException ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid RegisterDTO registerDTO) {
        try {
            this.authenticationService.register(registerDTO);
            return ResponseEntity.ok(new ResponseDTO(HttpStatus.OK.value(), "Usuário incluído com sucesso!"));
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
        }
    }
}

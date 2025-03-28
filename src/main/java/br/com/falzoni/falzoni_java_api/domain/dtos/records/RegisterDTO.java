package br.com.falzoni.falzoni_java_api.domain.dtos.records;

import br.com.falzoni.falzoni_java_api.domain.enums.UserRole;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Register", type = "object", description = "Objeto de registro de usuário")
public record RegisterDTO(String fullName, String userName, String password, String email, String phoneNumber, UserRole role) {
}

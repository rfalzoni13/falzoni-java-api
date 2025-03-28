package br.com.falzoni.falzoni_java_api.domain.dtos.records;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Login", type = "object", description = "Objeto de login")
public record LoginDTO(@Schema(description = "Login do usuário", type = "string", example = "User Test") String userName,
                       @Schema(description = "Senha do usuário", type = "string", example = "Test123") String password) {
}

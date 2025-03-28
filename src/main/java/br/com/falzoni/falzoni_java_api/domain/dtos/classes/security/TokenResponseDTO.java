package br.com.falzoni.falzoni_java_api.domain.dtos.classes.security;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "Token", type = "object", description = "Objeto do token")
public class TokenResponseDTO {

    @Schema(description = "Token de retorno", type = "string", example = "XxxXXXxXXXXXXxXXxXXXXXXxXxXXXxXxX-xXXXXXxxxxXXXXxXxXxXXxX")
    private String token;

    @Schema(description = "Data de expiração", type = "string", format = "date-time", example = "2024-02-01T10:00:00")
    private Instant expiresAt;

    @Schema(description = "Data de criação", type = "string", format = "date-time", example = "2024-02-10T12:00:00")
    private Instant issuedAt;

    public TokenResponseDTO(String token, Instant expiresAt, Instant issuedAt) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.issuedAt = issuedAt;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public Instant getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Instant issuedAt) {
        this.issuedAt = issuedAt;
    }
}

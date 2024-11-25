package br.com.falzoni.falzoni_java_api.domain.dtos.classes.security;

import java.time.Instant;

public class TokenResponseDTO {
    private String token;
    private Instant expiresAt;
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

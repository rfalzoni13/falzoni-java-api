package br.com.falzoni.falzoni_java_api.domain.dtos.records;

import br.com.falzoni.falzoni_java_api.domain.enums.UserRole;

public record RegisterDTO(String fullName, String userName, String password, String email, String phoneNumber, UserRole role) {
}

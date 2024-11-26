package br.com.falzoni.falzoni_java_api.domain.entities.security;

import br.com.falzoni.falzoni_java_api.domain.entities.base.BaseEntity;
import br.com.falzoni.falzoni_java_api.domain.enums.UserRole;
import jakarta.persistence.*;

import java.util.UUID;

@Entity
public class User extends BaseEntity {
    @Column(length = 256, nullable = false)
    private String fullName;

    @Column(length = 256, nullable = false)
    private String userName;

    @Column(columnDefinition = "TEXT")
    private String password;

    @Column(length = 256, nullable = false)
    private String email;

    @Column(length = 128, nullable = false)
    private String phoneNumber;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private UserRole role;

    public User() {
    }

    public User(String fullName, String userName, String password, String email, String phoneNumber, UserRole role) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() { return userName; }

    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public UserRole getRole() { return role; }

    public void setRole(UserRole role) { this.role = role; }
}

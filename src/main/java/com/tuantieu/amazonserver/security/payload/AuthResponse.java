package com.tuantieu.amazonserver.security.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class AuthResponse {
    private Long id;
    private String token;
    private String type = "Bearer";
    private String name;
    private Collection<? extends GrantedAuthority> roles;

    public AuthResponse() {
    }

    public AuthResponse(Long id, String token, String type, String name, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.type = type;
        this.name = name;
        this.roles = roles;
    }

    public AuthResponse(String token, String name, Collection<? extends GrantedAuthority> roles) {
        this.token = token;
        this.name = name;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getToken() {
        return token;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }
}

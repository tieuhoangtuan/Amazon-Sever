package com.tuantieu.amazonserver.security.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponse {
    private String token;
    private String name;
    private Collection<? extends GrantedAuthority> roles;
}

package com.tuantieu.amazonserver.security.payload;

import com.tuantieu.amazonserver.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequest {
    private String name;
    private String username;
    private String email;
    private String password;
    private List<Long> roleId;
}

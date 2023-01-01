package com.tuantieu.amazonserver.security.controller;

import com.tuantieu.amazonserver.entity.ResponseObject;
import com.tuantieu.amazonserver.entity.Role;
import com.tuantieu.amazonserver.entity.User;
import com.tuantieu.amazonserver.security.jwt.JwtTokenProvider;
import com.tuantieu.amazonserver.security.payload.AuthRequest;
import com.tuantieu.amazonserver.security.payload.AuthResponse;
import com.tuantieu.amazonserver.security.payload.SignUpRequest;
import com.tuantieu.amazonserver.security.userprinciple.UserPrinciple;
import com.tuantieu.amazonserver.service.impl.RoleServiceImpl;
import com.tuantieu.amazonserver.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/amazon/auth")
public class AuthController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignUpRequest signUpRequest){
        if(userService.existsByUsername(signUpRequest.getUsername())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("failed", "Username already existed", "")
            );
        }

        if (userService.existsByEmail(signUpRequest.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("failed", "Email already existed", "")
            );
        }

        User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), passwordEncoder.encode(signUpRequest.getPassword()));

        List<Role> roleList = new ArrayList<>();
        for(Long roleId: signUpRequest.getRoleId()){
            roleList.add(roleService.findById(roleId));
        }

        user.setRoles(roleList);
        userService.insertUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Sign up successfully", "")
        );
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenProvider.createToken(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return ResponseEntity.ok(new AuthResponse(jwtToken, userPrinciple.getName(), userPrinciple.getAuthorities()));
    }
}

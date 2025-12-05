package com.morphiq.morphiq.controller;

import com.morphiq.morphiq.dto.UserInfoResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/info")
    public UserInfoResponse getUserInfo(Authentication authentication) {
        if (authentication == null) {
            return new UserInfoResponse("anonymous", java.util.Collections.emptyList());
        }
        
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        java.util.List<String> roles = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .map(role -> role.replace("ROLE_", "")) // Remove ROLE_ prefix
                .collect(Collectors.toList());
        
        return new UserInfoResponse(authentication.getName(), roles);
    }
}
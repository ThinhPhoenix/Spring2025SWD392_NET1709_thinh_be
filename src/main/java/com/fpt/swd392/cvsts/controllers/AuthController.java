package com.fpt.swd392.cvsts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.swd392.cvsts.dto.request.SigninRequest;
import com.fpt.swd392.cvsts.dto.request.SignupRequest;
import com.fpt.swd392.cvsts.dto.response.JwtResponse;
import com.fpt.swd392.cvsts.dto.response.UserResponse;
import com.fpt.swd392.cvsts.entities.User;
import com.fpt.swd392.cvsts.enums.Role;
import com.fpt.swd392.cvsts.security.jwt.JwtUtils;
import com.fpt.swd392.cvsts.services.UserDetailsImpl;
import com.fpt.swd392.cvsts.services.UserService;
import com.fpt.swd392.cvsts.utils.ApiResponse;

import jakarta.validation.Valid;

import org.springframework.security.core.Authentication;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody SigninRequest loginRequest) {
        
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtCookie((UserDetailsImpl) authentication.getPrincipal()).toString();
            JwtResponse jwtResponse = new JwtResponse(jwt);
            ApiResponse<JwtResponse> response = new ApiResponse<>("200", jwtResponse, "Login successfully");
            return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
            Role role = Role.valueOf(signupRequest.getRole().toUpperCase());
            User user = userService.registerUser(signupRequest);
            UserResponse userResponse = new UserResponse(user);
            ApiResponse<UserResponse> response = new ApiResponse<>("201", userResponse, "User registered successfully!");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/me")
    public ResponseEntity<?> getUserByToken(@RequestHeader(name = "Authorization") String token) {
        try {
            // Remove the "Bearer " prefix from the token
            String jwtToken = token.substring(7);
            
            // Parse the token to get user's email
            String userId = jwtUtils.getUserNameFromJwtToken(jwtToken);
            
            // Fetch the user from the database
            User user = userService.getUserById(userId);
            
            // Return the user response
            UserResponse userResponse = new UserResponse(user);
            ApiResponse<UserResponse> response = new ApiResponse<>("200", userResponse, "User retrieved successfully");
            return ResponseEntity.ok(response);
            
        } catch (Exception e) {
            ApiResponse<?> errorResponse = new ApiResponse<>("401", null, "Invalid token or unauthorized access");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}

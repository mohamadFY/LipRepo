package com.Library.libraryManagementSystem.Service;


import com.Library.libraryManagementSystem.Repo.UserRepository;
import com.Library.libraryManagementSystem.dto.AuthenticationRequest;
import com.Library.libraryManagementSystem.dto.AuthenticationResponse;
import com.Library.libraryManagementSystem.dto.RegisterRequest;
import com.Library.libraryManagementSystem.type.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Service
@RequiredArgsConstructor
@EnableTransactionManagement
public class AuthenticationService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(String.valueOf(Role.ADMIN))
                .build();
        com.Library.libraryManagementSystem.model.User myUser = new com.Library.libraryManagementSystem.model.User();
        myUser.setUsername(user.getUsername());
        myUser.setPassword(user.getPassword());
        myUser.setRole(Role.ADMIN);
        userRepository.save(myUser);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}

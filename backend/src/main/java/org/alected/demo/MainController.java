package org.alected.demo;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/main")
@RestController
@CrossOrigin
public class MainController {
    @Autowired
    private LapRepository lapRepository;

    @GetMapping("/results")
    public ResponseEntity<?> mainPage() {
        List<LapData> rankings = lapRepository.findAll();
        return new ResponseEntity<>(rankings, HttpStatus.OK);
    }


    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> loginRequest) {
        String username = loginRequest.get("username");
        String password = loginRequest.get("password");
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    username, password);
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            String token = generateToken(authentication);
            return ResponseEntity.ok(token);
        } catch (AuthenticationException e) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }
    }

    private String generateToken(Authentication authentication) {
        return UUID.randomUUID().toString();
    }
}

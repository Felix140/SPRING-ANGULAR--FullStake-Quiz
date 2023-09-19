package com.quiz.fullstakequiz.configurations;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.quiz.fullstakequiz.dto.AdminDto;
import com.quiz.fullstakequiz.service.AdminService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;

//? Questa classe CREA e VALIDA la JWT
@RequiredArgsConstructor
@Component
public class AdminAuthProvider {

    @Value("${security.jwt.token.secret-key:secret-key}")
    private String secretKey;
    private final AdminService adminService;


    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(AdminDto admin) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1 hour

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(admin.getAdminName())
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .withClaim("firstName", admin.getFirstName())
                .withClaim("lastName", admin.getLastName())
                .sign(algorithm);
    }


    public Authentication validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        JWTVerifier verifier = JWT.require(algorithm)
                .build();
        DecodedJWT decoded = verifier.verify(token);
        AdminDto admin = AdminDto.builder()
                .adminName(decoded.getSubject())
                .firstName(decoded.getClaim("firstName").asString())
                .lastName(decoded.getClaim("lastName").asString())
                .build();
        return new UsernamePasswordAuthenticationToken(admin, null, Collections.emptyList());
    }


    public Authentication validateTokenStrongly(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        JWTVerifier verifier = JWT.require(algorithm)
                .build();

        DecodedJWT decoded = verifier.verify(token);

        AdminDto admin = adminService.findByAdminName(decoded.getSubject());

        return new UsernamePasswordAuthenticationToken(admin, null, Collections.emptyList());
    }

}

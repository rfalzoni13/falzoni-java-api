package br.com.falzoni.falzoni_java_api.configuration.security;

import br.com.falzoni.falzoni_java_api.domain.dtos.classes.security.TokenResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtConfig {
    @Autowired
    private JwtEncoder encoder;

    public TokenResponseDTO generateToken(Authentication authentication) {
        Instant now = Instant.now();
        long expiry = 3600L;

        String scopes = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("falzoni-java-web")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("scope", scopes)
                .build();

        Jwt jwt =  encoder.encode(JwtEncoderParameters.from(claims));
        return new TokenResponseDTO(
                jwt.getTokenValue(),
                jwt.getExpiresAt(),
                jwt.getIssuedAt()
        );
    }
}

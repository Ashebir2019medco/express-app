package com.api.epharmacy.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.api.epharmacy.exception.AppException;
import com.api.epharmacy.io.entity.UserEntity;
import com.api.epharmacy.io.repositories.UserRepository;
import com.api.epharmacy.ui.model.response.JwtAuthenticationResponse;
import com.api.epharmacy.ui.model.response.UserRest;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;
    
    @Value("${app.jwtPrefix}")
    private String jwtPrefix;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;


	@Autowired
	UserRepository userRepository;

    public UserEntity getUserByToken(HttpServletRequest request) {
    	try {
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && validateToken(jwt)) {
                Long userId = getUserIdFromJWT(jwt);

                Optional<UserEntity> userDetail = userRepository.findById(userId);
                if(userDetail.isPresent()) {
                	UserEntity userEntity=userDetail.get();
                	return userEntity;
                }
            }
        } catch (Exception ex) {
            throw new AppException("Could not find current user in security context");
        }
    	
    	return null;
    }
    public JwtAuthenticationResponse generateToken(Authentication authentication) {
    	
    	JwtAuthenticationResponse returnValue= new JwtAuthenticationResponse();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        
        String jwt =jwtPrefix + Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        
        returnValue.setAccessToken(jwt);
        returnValue.setUserId(userPrincipal.getUsername());
        returnValue.setUserType(userPrincipal.getUserType());
        returnValue.setUserStatus(userPrincipal.getUserStatus());
        
        return returnValue;
    }


    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }
    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            logger.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty.");
        }
        return false;
    }
}

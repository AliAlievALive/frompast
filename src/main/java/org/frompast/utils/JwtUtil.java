package org.frompast.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.UUID;

@UtilityClass
public class JwtUtil {

    public static UUID getUserGuid() {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return (UUID) authentication.getTokenAttributes().get("user_guid");
    }
}
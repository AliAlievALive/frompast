package org.frompast.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@UtilityClass
public class JwtUtil {

    public static String getUserGuid() {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        return authentication.getTokenAttributes().get("user_guid").toString();
    }
}
package com.engage.utilities;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.engage.jwtengage.config.JwtTokenUtil;
import com.engage.spring.pojo.JWTAuthorization;


@Component
public class AuthorizationUtilities {

    private static final String AUTHORIZATION = "authorization";
    Logger logger = LoggerFactory.getLogger(AuthorizationUtilities.class);

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public JWTAuthorization extractJWTUserId(Map<String, String> headers) {
        JWTAuthorization jwtAuthorization = new JWTAuthorization();
        HttpStatus errorCode = HttpStatus.BAD_REQUEST;
        String tkn = headers.get(AUTHORIZATION);
        String userId = null;
        if (tkn == null) {
            logger.info("No Authorazation token found ");
            jwtAuthorization.setHttpCode(errorCode);
        } else {
            if (tkn.length() > 7) {
                userId = jwtTokenUtil.getUsernameFromToken(tkn.substring(7));
                if (userId == null) {
                    jwtAuthorization.setHttpCode(errorCode);
                } else {  // A valid userid was found in the JWT token
                    jwtAuthorization.setHttpCode(HttpStatus.OK);
                    jwtAuthorization.setUserId(userId);
                }
            } else {
                jwtAuthorization.setHttpCode(errorCode);
            }
        }
        return jwtAuthorization;
    }


}

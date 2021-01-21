package com.engage.spring.pojo;

import org.springframework.http.HttpStatus;

public class JWTAuthorization {
    private HttpStatus httpCode;
    private String userId;

    public HttpStatus getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpStatus httpCode) {
        this.httpCode = httpCode;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
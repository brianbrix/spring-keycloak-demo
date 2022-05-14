package com.brianbrix.demo.services;

import com.brianbrix.demo.models.request.TokenRequest;
import com.brianbrix.demo.models.response.TokenResponse;

public interface TokenService {
    /**
     * Make request to keycloak token endpoint
     * @param tokenRequest
     * @return TokenResponse object
     */
    TokenResponse getToken(TokenRequest tokenRequest);
}

package com.brianbrix.demo.services;

import com.brianbrix.demo.models.request.TokenRequest;
import com.brianbrix.demo.models.response.TokenResponse;

public interface TokenService {
TokenResponse getToken(TokenRequest tokenRequest);
}

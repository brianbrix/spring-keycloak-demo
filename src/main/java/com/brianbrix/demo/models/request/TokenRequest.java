package com.brianbrix.demo.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class TokenRequest {
    @JsonProperty("client_id")
    private String clientId;
    private String username;
    private String password;
    @JsonProperty("grant_type")
    private String grantType;
}

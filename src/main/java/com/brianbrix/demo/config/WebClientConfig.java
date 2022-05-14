package com.brianbrix.demo.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {
    private static final int TIMEOUT = 300;

    static HttpClient client = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 300000)
            .doOnConnected(conn -> conn
                    .addHandler(new ReadTimeoutHandler(TIMEOUT, TimeUnit.SECONDS))
                    .addHandler(new WriteTimeoutHandler(TIMEOUT, TimeUnit.SECONDS)));

    @Bean
    public WebClient myWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
    }

}

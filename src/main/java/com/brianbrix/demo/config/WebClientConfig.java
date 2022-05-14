package com.brianbrix.demo.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import javax.net.ssl.SSLException;
import java.util.concurrent.TimeUnit;

@Configuration
public class WebClientConfig {
    private static final int TIMEOUT = 300;



    @Bean
    public WebClient myWebClient(WebClient.Builder webClientBuilder) throws SSLException {
        SslContext sslContext = SslContextBuilder
                .forClient()
                .trustManager(InsecureTrustManagerFactory.INSTANCE)
                .build();
        HttpClient client = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 300000)
                .secure(t->t.sslContext(sslContext))
                .doOnConnected(conn -> conn
                        .addHandler(new ReadTimeoutHandler(TIMEOUT, TimeUnit.SECONDS))
                        .addHandler(new WriteTimeoutHandler(TIMEOUT, TimeUnit.SECONDS)));
        return webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(client))
                .build();
    }

}

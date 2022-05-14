package com.brianbrix.demo.utils;

import com.brianbrix.demo.exceptions.ServiceException;
import io.netty.handler.timeout.ReadTimeoutException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.List;

@Log4j2
public class GenericWebClient<T, V> {
    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(List.of((MediaType.APPLICATION_FORM_URLENCODED)));
//        headers.set("Authorization", authToken);
        return headers;
    }

    public T makeRequest(WebClient webClient, String base, String path, V req, Class<T> clazz)
    {
        return webClient.post()
                .uri(base, builder -> builder.path(path).build())
                .headers(httpHeaders -> httpHeaders.addAll(createHeaders()))
                .bodyValue(req)
                .retrieve()
                .onStatus(
                        HttpStatus::is4xxClientError,
                        response -> response.bodyToMono(String.class).flatMap(body -> {
                            log.info("Body is {}", body);
                            return Mono.error(new ServiceException(body, response.rawStatusCode()));
                        }))
                .bodyToMono(clazz)
                .retryWhen(Retry.backoff(3, Duration.ofSeconds(2))
                        .filter(throwable -> throwable instanceof io.netty.handler.timeout.ReadTimeoutException))
                .doOnError(ReadTimeoutException.class, e ->{//change it to io.netty.handler.timeout.ReadTimeoutException
                    throw new ServiceException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.ordinal());
                })
                .block();
    }

}
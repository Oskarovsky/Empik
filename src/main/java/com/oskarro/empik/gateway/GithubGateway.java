package com.oskarro.empik.gateway;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Component class that uses reactive Webflux
 * It is non-blocking by nature and the response will always be returned in either Mono or Flux.
 *
 * */

@Component
public class GithubGateway {

    @Value("${github.url}")
    private String githubUrl;

    /**
     * @param login username from github
     * @return Content from Github API related to specific user login (returns 0 or 1 element).
     * */
    public Mono<String> getUserDataFromGithub(String login) {
        return WebClient.create(githubUrl)
                .get()
                .uri(uriBuilder -> uriBuilder.path(login).build())
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(String.class);
    }

}

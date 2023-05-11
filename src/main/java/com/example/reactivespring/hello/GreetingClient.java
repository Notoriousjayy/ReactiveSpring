package com.example.reactivespring.hello;

import reactor.core.publisher.Mono;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
/*
The Spring RestTemplate class is, by nature, blocking.
Consequently, we do not want to use it in a reactive application.
For reactive applications, Spring offers the WebClient class, which is non-blocking.
We use a WebClient-based implementation to consume our RESTful service:
 */

@Component
public class GreetingClient {
    private final WebClient client;

    // Spring Boot auto-configures a `WebClient.Builder` instance with nice defaults and customizations.
    // We can use it to create a dedicated `WebClient` for our component.
    public GreetingClient(WebClient.Builder builder) {
        this.client = builder.baseUrl("http://localhost:8080").build();
    }

    public Mono<String> getMessage() {
        return this.client.get().uri("/hello").accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Greeting.class)
                .map(Greeting::getMessage);
    }
}

/*
The WebClient class uses reactive features, in the form of a Mono
to hold the content of the message (returned by the getMessage method).
This is using a function API, rather than an imperative one, to chain reactive operators.

It can take time to get used to Reactive APIs, but the WebClient has
interesting features and can also be used in traditional Spring MVC applications.
 */

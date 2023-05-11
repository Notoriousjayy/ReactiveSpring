package com.example.reactivespring.hello;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

// we use a router to handle the only route we expose (/hello)
@Configuration(proxyBeanMethods = false)
public class GreetingRouter {
/*
The router listens for traffic on the /hello path and returns the value provided by our reactive handler class.
 */
    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {
        return RouterFunctions
                .route(GET("/hello").and(accept(MediaType.APPLICATION_JSON)), greetingHandler::hello);
    }
}

package com.example.reactivespring;

import com.example.reactivespring.hello.GreetingClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ReactiveSpringApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ReactiveSpringApplication.class, args);
        GreetingClient greetingClient = context.getBean(GreetingClient.class);
        // We need to block for the content here or the JVM might exit before the message is logged
        System.out.println(">> message = " + greetingClient.getMessage().block());
    }

}
/*
@SpringBootApplication is a convenience annotation that adds all of the following:

@Configuration: Tags the class as a source of bean definitions for the application context.

@EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings,
other beans, and various property settings. For example, if spring-webmvc is on the classpath,
this annotation flags the application as a web application and activates key behaviors,
such as setting up a DispatcherServlet.

@ComponentScan: Tells Spring to look for other components, configurations,
and services in the hello package, letting it find the controllers.

The main() method uses Spring Boot’s SpringApplication.run() method to
launch an application. Did you notice that there was not a single line of XML?
There is no web.xml file, either. This web application is 100% pure Java and you
did not have to deal with configuring any plumbing or infrastructure.
 */
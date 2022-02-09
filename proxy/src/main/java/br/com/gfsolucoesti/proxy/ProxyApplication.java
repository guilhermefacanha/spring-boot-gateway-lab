package br.com.gfsolucoesti.proxy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}
	
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	    return builder.routes()
	        .route(p -> p
	            .path("/get")
	            .filters(f -> f
	                .addRequestHeader("Hello", "World")
	                .addRequestHeader("TriggerTime", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss").format(LocalDateTime.now())))
	            .uri("http://httpbin.org:80"))
	        .route(p-> p
	            .path("/available")
	            .uri("http://localhost:8080/available")
	            )
	        .route(p-> p
	            .path("/checked-out")
	            .uri("http://localhost:8080/checked-out")
	            )
	        .route(p-> p
	            .path("/checked-out")
	            .uri("http://localhost:8080/checked-out")
	            )
	        .route("app", p-> p
	            .order(1)
	            .path("/app/**")
	            .filters(f->f
	                .rewritePath("/app/(?<segment>.*)", "/site/${segment}"))
	            .uri("http://localhost:8080")
	            )
	        .route("default", p-> p
	            .order(2)
	            .path("/**").and().header("Referer",".*/app/.*")
	            .filters(f->f
	                .rewritePath("/(?<segment>.*)", "/${segment}"))
	            .uri("http://localhost:8080")
	            )
	        .build();
	}

}

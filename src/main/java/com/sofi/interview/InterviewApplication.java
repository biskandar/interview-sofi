package com.sofi.interview;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@SpringBootApplication
public class InterviewApplication {

    @Bean
    HealthIndicator healthIndicator() {
        return () -> Health.status("I <3 Production").build();
    }

    @Bean
    RouterFunction<ServerResponse> routeTransactions(TransactionReactiveRepository transactionRepository) {
        return RouterFunctions
                .route(GET("/transactions")
                        , request -> ok().body(transactionRepository.findAll(), Transaction.class))
                .andRoute(GET("/transactions/{id}")
                        , request -> ok().body(transactionRepository.findById(Integer.parseInt(request.pathVariable("id"))), Transaction.class))
                .andRoute(POST("/transactions")
                        , request -> request.bodyToMono(Transaction.class).doOnNext(transactionRepository::save).then(ok().build()));
    }

    public static void main(String[] args) {
        SpringApplication.run(InterviewApplication.class, args);
    }

}


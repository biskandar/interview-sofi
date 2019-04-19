package com.sofi.interview;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TransactionReactiveRepository
        extends ReactiveMongoRepository<Transaction, Integer> {


}

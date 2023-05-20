package com.example.crud.Configuration;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableAutoConfiguration
@Configuration
public class MongoConfiguration {
    public MongoClient mongoClient(){
        return MongoClients.create("mongodb+srv://franky:5KFVf13wFee7aQ0x@cluster0.2dh4g.mongodb.net/crud?retryWrites=true&w=majority");
    }

    public @Bean
    MongoTemplate mongoTemplate(){
        return new MongoTemplate(mongoClient(), "crud");
    }
}

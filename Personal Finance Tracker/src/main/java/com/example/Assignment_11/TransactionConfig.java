package com.example.Assignment_11;

import com.example.Assignment_11.model.Transaction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class TransactionConfig {
    @Bean
    public List<Transaction> transactions(){
        return new ArrayList<Transaction>();
    }
}

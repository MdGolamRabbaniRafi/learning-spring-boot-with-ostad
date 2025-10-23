package com.example.Assignment_11.service;

import com.example.Assignment_11.model.Transaction;
import com.example.Assignment_11.model.TransactionType;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionsService {
    private final List<Transaction> transactions;

    public ResponseEntity<Transaction> add(Transaction transaction)
    {
        transactions.add(transaction);
        return ResponseEntity.ok(transaction);
    }

    public ResponseEntity<Transaction> edit(Long id,Transaction transaction)
    {
        Transaction getTransaction = this.transactions.stream()
                .filter(tr -> {return tr.getId().equals(id);})
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
        transactions.remove(getTransaction);
        transactions.add(transaction);
        return ResponseEntity.ok(transaction);

    }

    public boolean remove(Long id)
    {
        Transaction transaction = this.transactions.stream()
                .filter(tr -> {return tr.getId().equals(id);})
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));
        transactions.remove(transaction);
        return true;
    }

    public ResponseEntity<List<Transaction>> getAll()
    {
        return ResponseEntity.ok(this.transactions);
    }

    public ResponseEntity<Transaction> getOne(Long id)
    {
        Transaction transaction = this.transactions.stream()
                .filter(tr -> tr.getId().equals(id))
                .findFirst()
                .orElse(null);

        return ResponseEntity.ok(transaction);

    }

    public ResponseEntity<List<Transaction>> getAllOrByType(String type) {
        List<Transaction> result;

        if (type != null) {
            result = this.transactions.stream()
                    .filter(tr -> tr.getType().name().equalsIgnoreCase(type))
                    .toList();
        } else {
            result = this.transactions;
        }

        return ResponseEntity.ok(result);
    }

}

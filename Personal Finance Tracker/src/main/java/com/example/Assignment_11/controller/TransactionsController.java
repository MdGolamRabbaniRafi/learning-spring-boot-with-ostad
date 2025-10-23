package com.example.Assignment_11.controller;

import com.example.Assignment_11.model.Transaction;
import com.example.Assignment_11.model.TransactionType;
import com.example.Assignment_11.service.TransactionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionsController {
  private final TransactionsService service;

    @PostMapping()
    public ResponseEntity<Transaction> add(@RequestBody() Transaction transaction)
    {
        return this.service.add(transaction);
    }

  @PutMapping("/{id}")
  public ResponseEntity<Transaction> edit(@PathVariable() Long id, @RequestBody() Transaction transactions)
  {
    System.out.println(((Object) id).getClass().getSimpleName());
    return this.service.edit(id,transactions);
  }
  @DeleteMapping("/{id}")
  public boolean remove(@PathVariable() Long id)
  {
    return this.service.remove(id);
  }
//  @GetMapping()
//  public ResponseEntity<List<Transaction>> getAll()
//  {
//    return this.service.getAll();
//  }
  @GetMapping("{id}")
  public ResponseEntity<Transaction> getOne(@PathVariable() Long id)
  {
    return this.service.getOne(id);
  }

  @GetMapping
  public ResponseEntity<List<Transaction>> getAllOrByType(@RequestParam(required = false) String type) {
    return this.service.getAllOrByType(type);
  }

  @GetMapping("/test")
  public String test() {
    return "Controller is working!";
  }



}

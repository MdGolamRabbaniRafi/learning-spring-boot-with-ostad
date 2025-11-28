package com.springSecurity.springSecurity.Controller;

import com.springSecurity.springSecurity.Entity.BookEntity;
import com.springSecurity.springSecurity.Response.ApiResponse;
import com.springSecurity.springSecurity.Service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Validated
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<BookEntity>> createBook(@Valid @RequestBody BookEntity book)
    {
        return ResponseEntity.status(201).body(bookService.createBook(book));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookEntity>>> findAll()
    {
        return ResponseEntity.ok(bookService.findall());
    }
    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookEntity>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.findById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookEntity>> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody BookEntity book) {

        return ResponseEntity.ok(bookService.updateBook(id, book));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.deleteBook(id));
    }

}

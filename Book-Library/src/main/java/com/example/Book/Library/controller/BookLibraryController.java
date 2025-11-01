package com.example.Book.Library.controller;

import com.example.Book.Library.Entity.BookLibrary;
import com.example.Book.Library.service.BookLibraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookLibraryController {
    private final BookLibraryService bookLibraryService;

    @PostMapping()
    public ResponseEntity<BookLibrary> add(@RequestBody BookLibrary bookLibrary)
    {
        return ResponseEntity.ok(bookLibraryService.add(bookLibrary));
    }

    @GetMapping()
    public ResponseEntity<List<BookLibrary>> getAll()
    {
        return ResponseEntity.ok(bookLibraryService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookLibrary> getBookById(@PathVariable Long id)
    {
        return ResponseEntity.ok(bookLibraryService.getBookById(id));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id)
    {
        bookLibraryService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookLibrary> update(@PathVariable Long id, @RequestBody BookLibrary bookLibrary)
    {
        return ResponseEntity.ok(bookLibraryService.update(id,bookLibrary));
    }

    @GetMapping("/author/{authorName}")
    public ResponseEntity<List<BookLibrary>> getBooksByAuthor(@PathVariable String authorName) {
        return ResponseEntity.ok(bookLibraryService.getBooksByAuthor(authorName));
    }

    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<BookLibrary>> getBooksByGenre(@PathVariable String genre) {
        return ResponseEntity.ok(bookLibraryService.getBooksByGenre(genre));
    }

    @GetMapping("/publication/{publication}")
    public ResponseEntity<List<BookLibrary>> getBooksByPublication(@PathVariable String publication) {
        return ResponseEntity.ok(bookLibraryService.getBooksByPublication(publication));
    }

    @PostMapping("/test")
    public String test()
    {
        return "api is healthy";
    }
}

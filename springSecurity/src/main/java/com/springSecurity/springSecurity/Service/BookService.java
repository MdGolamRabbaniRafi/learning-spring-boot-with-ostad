package com.springSecurity.springSecurity.Service;

import com.springSecurity.springSecurity.Exception.BookNotFoundException;
import com.springSecurity.springSecurity.Exception.NotNullException;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import com.springSecurity.springSecurity.Entity.BookEntity;
import com.springSecurity.springSecurity.Repository.BookRepository;
import com.springSecurity.springSecurity.Response.ApiResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
    private final BookRepository bookRepository;

    public ApiResponse<BookEntity> createBook(BookEntity book)
    {
        if(!StringUtils.hasText(book.getTitle()))
        {
            throw new NotNullException("Title must be provided");

        }
        if(!StringUtils.hasText(book.getAuthor()))
        {
            throw new NotNullException("Author must be provided");

        }
        if (book.getAvailableCopies() == null || book.getAvailableCopies() < 0) {
            throw new NotNullException("Available copies must be provided and >= 0");
        }

        BookEntity savedBook = bookRepository.save(book);

        return new ApiResponse<>(
                "success",
                HttpStatus.CREATED.value(),
                "Book created successfully",
                false,
                savedBook
        );

    }

    public ApiResponse<List<BookEntity>> findall()
    {
        List<BookEntity> books = bookRepository.findAll();

        return new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Fetched all books successfully",
                false,
                books
        );
    }

    public ApiResponse<BookEntity> findById(Long id) {
        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        return new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Fetched book successfully",
                false,
                book
        );
    }

    public ApiResponse<BookEntity> updateBook(Long id, BookEntity updatedBook) {

        BookEntity existing = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        if (StringUtils.hasText(updatedBook.getTitle())) {
            existing.setTitle(updatedBook.getTitle());
        }

        if (StringUtils.hasText(updatedBook.getAuthor())) {
            existing.setAuthor(updatedBook.getAuthor());
        }

        if (updatedBook.getAvailableCopies() != null && updatedBook.getAvailableCopies() >= 0) {
            existing.setAvailableCopies(updatedBook.getAvailableCopies());
        }

        BookEntity saved = bookRepository.save(existing);

        return new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Book updated successfully",
                false,
                saved
        );
    }

    public ApiResponse<Void> deleteBook(Long id) {

        BookEntity book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + id));

        bookRepository.delete(book);

        return new ApiResponse<>(
                "success",
                HttpStatus.OK.value(),
                "Book deleted successfully",
                false,
                null
        );
    }


}

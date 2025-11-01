package com.example.Book.Library.service;

import com.example.Book.Library.Entity.BookLibrary;
import com.example.Book.Library.repository.BookLibraryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookLibraryService {
    private final BookLibraryRepository bookLibraryRepository;

    public BookLibrary add(BookLibrary bookLibrary)
    {
       return bookLibraryRepository.save(bookLibrary);
    }

    public List<BookLibrary> getAll()
    {
        return bookLibraryRepository.findAll();
    }

    public BookLibrary getBookById(Long id)
    {
        return bookLibraryRepository.findById(id).orElseThrow(()-> new RuntimeException("Invalid Id"));
    }

    public void delete(Long id)
    {
        bookLibraryRepository.deleteById(id);

    }

    public BookLibrary update(Long id, BookLibrary bookLibrary)
    {
       BookLibrary bookLibrary1 = bookLibraryRepository.findById(id).orElseThrow(()-> new RuntimeException("Invalid Id"));

       bookLibrary.setId(bookLibrary1.getId());
       if(bookLibrary.getTitle()!=null)
       {
           bookLibrary1.setTitle(bookLibrary.getTitle());
       }

        if(bookLibrary.getAuthor()!=null)
        {
            bookLibrary1.setAuthor(bookLibrary.getAuthor());
        }

        if(bookLibrary.getGenre()!=null)
        {
            bookLibrary1.setGenre(bookLibrary.getGenre());
        }

        if(bookLibrary.getPublication()!=null)
        {
            bookLibrary1.setPublication(bookLibrary.getPublication());
        }

        if(bookLibrary.getPublicationYear()!=null)
        {
            bookLibrary1.setPublicationYear(bookLibrary.getPublicationYear());
        }

        if(bookLibrary.getAvailableCopies()!=null)
        {
            bookLibrary1.setAvailableCopies(bookLibrary.getAvailableCopies());
        }

        return bookLibraryRepository.save(bookLibrary1);


    }

    public List<BookLibrary> getBooksByAuthor(String author) {
        return bookLibraryRepository.findByAuthorIgnoreCase(author);
    }

    public List<BookLibrary> getBooksByGenre(String genre) {
        return bookLibraryRepository.findByGenreIgnoreCase(genre);
    }

    public List<BookLibrary> getBooksByPublication(String publication) {
        return bookLibraryRepository.findByPublicationIgnoreCase(publication);
    }
}

package com.example.Book.Library.repository;

import com.example.Book.Library.Entity.BookLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookLibraryRepository extends JpaRepository<BookLibrary,Long> {
    List<BookLibrary> findByAuthorIgnoreCase(String author);
    List<BookLibrary> findByGenreIgnoreCase(String genre);
    List<BookLibrary> findByPublicationIgnoreCase(String publication);
}

package com.library.repository;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    public void save(String book) {
        System.out.println("Saving book: " + book);
    }

    public String findById(Long id) {
        return "Book{id=" + id + ", title='Sample Book', author='John Doe'}";
    }
}

package com.Library.libraryManagementSystem.repository;

import com.Library.libraryManagementSystem.Repo.BookRepository;
import com.Library.libraryManagementSystem.model.Book;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void bookRepo_SaveBook_ReturnBook(){
        Book book = new Book("newLife","cris","2022","6565633");
        Book savedBook = bookRepository.save(book);
        Assertions.assertThat(savedBook).isNotNull();
        Assertions.assertThat(savedBook.getId()).isGreaterThan(0);
    }
    @Test
    public void bookRepo_FindById_ReturnBook(){
        Book book = new Book("newLife","cris","2022","6565633");
        bookRepository.save(book);
        Book foundedBook = bookRepository.findById(book.getId()).orElseThrow();
        Assertions.assertThat(foundedBook).isNotNull();
    }
    @Test
    public void bookRepo_FindAll_ReturnBookList(){
        Book book = new Book("newLife","cris","2022","6565633");
        Book book1 = new Book("newWorld","cris","2023","6565634");
        bookRepository.save(book);
        bookRepository.save(book1);

        List<Book> allBook = bookRepository.findAll();

        Assertions.assertThat(allBook).isNotNull();
        Assertions.assertThat(allBook.size()).isEqualTo(2);

    }
}

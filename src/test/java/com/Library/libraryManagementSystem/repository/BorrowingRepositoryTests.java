package com.Library.libraryManagementSystem.repository;

import com.Library.libraryManagementSystem.Repo.BookRepository;
import com.Library.libraryManagementSystem.Repo.BorrowRepository;
import com.Library.libraryManagementSystem.Repo.PatronRepository;
import com.Library.libraryManagementSystem.model.Book;
import com.Library.libraryManagementSystem.model.BorrowingRecord;
import com.Library.libraryManagementSystem.model.Patron;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BorrowingRepositoryTests {
    @Autowired
    private BorrowRepository borrowRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private PatronRepository patronRepository;

    @Test
    public void borrowRepo_SaveBorrow_ReturnSaved() throws Exception {
        Book book = new Book("newLife","cris","2022","6565633");
        book = bookRepository.save(book);
        Patron patron = new Patron("mhd","0930321577","mhd@gmail.com");
        patron = patronRepository.save(patron);
        BorrowingRecord borrowingRecord = new BorrowingRecord(book,patron,"","");

        BorrowingRecord saved = borrowRepository.save(borrowingRecord);
        Assertions.assertThat(saved).isNotNull();

        BorrowingRecord find = borrowRepository.findByBookIdAndPatronId(book.getId(),patron.getId());
        Assertions.assertThat(find).isNotNull();
        Assertions.assertThat(find.getId()).isGreaterThan(0);
    }
}

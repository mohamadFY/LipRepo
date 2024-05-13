package com.Library.libraryManagementSystem.Repo;

import com.Library.libraryManagementSystem.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {
//    Book findById(long id);
}

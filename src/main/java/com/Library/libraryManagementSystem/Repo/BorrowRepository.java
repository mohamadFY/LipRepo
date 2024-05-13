package com.Library.libraryManagementSystem.Repo;

import com.Library.libraryManagementSystem.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRepository extends JpaRepository<BorrowingRecord,Long> {

    public BorrowingRecord findByBookIdAndPatronId(long bookId,long patronId);
    public BorrowingRecord findByBookId(long id);
    public BorrowingRecord findByPatronId(long id);

}

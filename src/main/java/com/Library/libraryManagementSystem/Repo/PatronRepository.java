package com.Library.libraryManagementSystem.Repo;

import com.Library.libraryManagementSystem.model.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends JpaRepository<Patron,Long> {
//    Patron findById(long id);
}

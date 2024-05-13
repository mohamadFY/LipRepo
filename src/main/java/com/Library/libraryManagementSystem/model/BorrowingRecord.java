package com.Library.libraryManagementSystem.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Borrowing_Record")
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Patron patron;

    private String borrowDate;
    private String returnDate;


    public BorrowingRecord(Book book, Patron patron, String  borrowDate,String returnDate) {
        this.book = book;
        this.patron = patron;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }


}

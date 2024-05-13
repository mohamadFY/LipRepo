package com.Library.libraryManagementSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
import lombok.*;
import org.springframework.web.ErrorResponse;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "title" , unique = true)
    @NotEmpty(message = "title must not be empty")
    private String title;
    @NotEmpty(message = "author must not be empty")
    private String author;
    @Min(value = 1500 ,message = "Invalid Year")
    @Max(value = 2024 ,message = "Invalid Year")
    private String publicationYear;
    @Column(name = "isbn" , unique = true)
    @NotEmpty(message = "isbn must be valid")
    private String isbn;


    public Book(String title, String author, String publicationYear, String isbn) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isbn = isbn;
    }



}

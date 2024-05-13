package com.Library.libraryManagementSystem.Controllers;

import com.Library.libraryManagementSystem.Repo.BookRepository;
import com.Library.libraryManagementSystem.Service.BookService;
import com.Library.libraryManagementSystem.model.Book;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/")
    public ResponseEntity<? extends Object> allBooks(){
        return new ResponseEntity<>(bookService.getAllBook(), HttpStatus.OK);
    }

    @Operation(summary = "Get a book by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the book",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Book.class)) }),
            @ApiResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })
    @GetMapping("/{id}")
    public ResponseEntity<?> BookById(@PathVariable long id) throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity<>(bookService.getBookById(id),HttpStatus.OK);
    }


    @PostMapping("/")
    public ResponseEntity<?> addBook(@RequestBody @Valid Book book){
        return new ResponseEntity<>(bookService.addBook(book),HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable long id , @RequestBody @Valid Book book) throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity<>(bookService.updateBook(id,book),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<? extends Object> deleteById(@PathVariable long id) throws Exception {
        try {
            return new ResponseEntity<Boolean>(bookService.deleteBook(id),HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<String>("CAN'T DELETE BOOK",HttpStatus.BAD_REQUEST);
        }
    }

}

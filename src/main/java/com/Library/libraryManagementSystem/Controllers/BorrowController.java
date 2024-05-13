package com.Library.libraryManagementSystem.Controllers;

import com.Library.libraryManagementSystem.Service.BorrowService;
import com.Library.libraryManagementSystem.model.BorrowingRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api")
@EnableTransactionManagement
public class BorrowController {

    @Autowired
    BorrowService borrowService;

    @PostMapping(value = "/borrow/{bookId}/patron/{patronId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends Object> borrow(@PathVariable long bookId,@PathVariable long patronId) throws Exception {
        try {
            return new ResponseEntity<>(borrowService.borrowBook(bookId,patronId), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Invalid Input",HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping(value = "/return/{bookId}/patron/{patronId}" , produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<? extends Object> returnBook(@PathVariable long bookId,@PathVariable long patronId) throws Exception {
        try {
            return new ResponseEntity<>(borrowService.returnBook(bookId, patronId), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Invalid Input",HttpStatus.BAD_REQUEST);
        }
    }
}

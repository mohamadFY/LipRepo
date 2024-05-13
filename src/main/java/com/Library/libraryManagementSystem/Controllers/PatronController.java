package com.Library.libraryManagementSystem.Controllers;

import com.Library.libraryManagementSystem.Repo.PatronRepository;
import com.Library.libraryManagementSystem.Service.PatronService;
import com.Library.libraryManagementSystem.model.Patron;
import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
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
@RequestMapping("/api/patrons")
public class PatronController {

    @Autowired
    PatronService patronService;
    @GetMapping("/")
    public ResponseEntity<?> getAllPatron(){
        return new ResponseEntity<>(patronService.getAllPatron(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatronById(@PathVariable long id) throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity<>(patronService.getPatronById(id),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<? extends Object> addPatron(@RequestBody @Valid Patron patron){
        try {
            return new ResponseEntity<>(patronService.addPatron(patron),HttpStatus.CREATED);
        }
        catch (Exception ex){
            return new ResponseEntity<>("duplicate Phone number",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patron> update(@PathVariable long id,@RequestBody @Valid Patron patron) throws ChangeSetPersister.NotFoundException {
        return new ResponseEntity<>(patronService.updatePatron(id,patron),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<? extends Object> deleteById(@PathVariable long id) throws Exception {
        try {
            return new ResponseEntity<>(patronService.deletePatron(id), HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("CAN'T DELETE PATRON",HttpStatus.BAD_REQUEST);
        }
    }

}

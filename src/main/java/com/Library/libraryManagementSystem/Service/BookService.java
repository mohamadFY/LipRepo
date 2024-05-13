package com.Library.libraryManagementSystem.Service;

import com.Library.libraryManagementSystem.Repo.BookRepository;
import com.Library.libraryManagementSystem.Repo.BorrowRepository;
import com.Library.libraryManagementSystem.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BorrowRepository borrowRepository;

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public Book getBookById(long id) throws ChangeSetPersister.NotFoundException {
        Book mybook = bookRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return mybook;
    }

    public Book addBook(Book book){
//        try {
            return bookRepository.save(book);
//        }
//        catch (Exception e){
//            throw e;
//        }

    }

    public Book updateBook(long id ,Book newBook) throws ChangeSetPersister.NotFoundException {
        Book book = bookRepository.findById(id).orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        book.setAuthor(newBook.getAuthor());
        book.setIsbn(newBook.getIsbn());
        book.setTitle(newBook.getTitle());
        book.setPublicationYear(newBook.getPublicationYear());
        return bookRepository.save(book);
    }

    public boolean deleteBook(long id) throws Exception {
        if (borrowRepository.findByBookId(id)==null) {
            if (bookRepository.findById(id).isPresent()) {
                bookRepository.deleteById(id);
                return true;
            }
            else throw new Exception("CANT DELETE BOOK");
        }
        else throw new Exception("CANT DELETE BOOK");
    }
}

package com.Library.libraryManagementSystem.Service;

import com.Library.libraryManagementSystem.Repo.BorrowRepository;
import com.Library.libraryManagementSystem.Repo.PatronRepository;
import com.Library.libraryManagementSystem.model.Patron;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PatronService {

    @Autowired
    private PatronRepository patronRepository;
    @Autowired
    private BorrowRepository borrowRepository;

    public List<Patron> getAllPatron(){
        return patronRepository.findAll();
    }

    public Patron getPatronById(long id) throws ChangeSetPersister.NotFoundException {
        return patronRepository.findById(id).orElseThrow(()-> new ChangeSetPersister.NotFoundException());
    }

    @Transactional
    public Patron addPatron(Patron patron){
        return patronRepository.save(patron);
    }

    public Patron updatePatron(long id , Patron newPatron) throws ChangeSetPersister.NotFoundException {
        Patron patron = patronRepository.findById(id).orElseThrow(()-> new ChangeSetPersister.NotFoundException());
        patron.setName(newPatron.getName());
        patron.setPhoneNo(newPatron.getPhoneNo());
        return patronRepository.save(patron);
    }

    public boolean deletePatron(long id) throws Exception {
        if (borrowRepository.findByPatronId(id)==null) {
            if (patronRepository.findById(id).isPresent()) {
                patronRepository.deleteById(id);
                return true;
            }
            else {
                throw new Exception("Book Not Found");
            }
        }
        else {
            throw new Exception("CANT DELETE BOOK");
        }
    }


}

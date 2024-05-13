package com.Library.libraryManagementSystem.repository;

import com.Library.libraryManagementSystem.Repo.PatronRepository;
import com.Library.libraryManagementSystem.model.Patron;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class PatronRepositoryTests {
    @Autowired
    private PatronRepository patronRepository;

    @Test
    public void patronRepo_SavePatron_ReturnSavedPatron(){
        Patron patron = new Patron("mhd","0930321577","mhd@gmail.com");
        Patron savedPatron = patronRepository.save(patron);
        Assertions.assertThat(savedPatron).isNotNull();
        Assertions.assertThat(savedPatron.getId()).isGreaterThan(0);
    }
    @Test
    public void patronRepo_FindById_ReturnPatron(){
        Patron patron = new Patron("mhd","0930321577","mhd@gmail.com");
        patronRepository.save(patron);
        Patron foundedPatron = patronRepository.findById(patron.getId()).orElseThrow();
        Assertions.assertThat(foundedPatron).isNotNull();
    }
    @Test
    public void PatronRepo_FindAll_ReturnPatronList(){
        Patron patron = new Patron("mhd","0930321577","mhd@gmail.com");
        Patron patron1 = new Patron("Salman","0930981455","salmamn@gmail.com");
        patronRepository.save(patron);
        patronRepository.save(patron1);
        List<Patron> allPatron = patronRepository.findAll();
        Assertions.assertThat(allPatron).isNotNull();
        Assertions.assertThat(allPatron.size()).isEqualTo(2);
    }
}

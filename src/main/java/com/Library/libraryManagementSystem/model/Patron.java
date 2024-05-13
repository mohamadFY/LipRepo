package com.Library.libraryManagementSystem.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Patron" , uniqueConstraints = { @UniqueConstraint(columnNames = { "phone_no", "isActive" }) })
public class Patron {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotEmpty(message = "Name must not be empty")
    private String name;
    @Column( unique = true)
    @Pattern(regexp = "^\\d{10}$" , message = "Invalid Phone Number")
    private String phoneNo;
    @Email(message = "invalid Email Address")
    private String email;


    public Patron(String name, String phoneNo , String email) {
        this.name = name;
        this.phoneNo = phoneNo;
        this.email = email;
    }


}

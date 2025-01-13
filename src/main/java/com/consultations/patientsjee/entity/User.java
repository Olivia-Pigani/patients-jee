package com.consultations.patientsjee.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "subscribers")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(nullable = false,unique = true)
    private String email;

    // We don't stock clearly passwords anymore, we hash them with PBKDF2
    //    @Column(nullable = false)
    //    private String password;

    @Column(name = "hashed_password", nullable = false)
    private byte[] hashedPassword;

    @Column(nullable = false)
    private byte[] salt;


}

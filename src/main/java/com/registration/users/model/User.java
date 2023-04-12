package com.registration.users.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * This class represents a registered user in the system.
 */
@Data
@Entity
@Table(name = "user_register")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the user.
     */
    @Column(nullable = false)
    private String name;

    /**
     * The date of birth of the user.
     */
    @Column(name="date_birth", nullable = false)
    private LocalDate dateBirth;

    /**
     * The country of residence of the user.
     */
    @Column(name="country_residence", nullable = false)
    private String countryResidence;

    /**
     * The phone number of the user.
     */
    @Column(name="phone_number")
    private String phoneNumber;

    /**
     * The gender of the user.
     */
    private String gender;
}
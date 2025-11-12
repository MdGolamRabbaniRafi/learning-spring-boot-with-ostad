package com.example.Contact.Manager.API.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String phoneNo;

    private String email;

    private Boolean isActive;

    private String category; // e.g. work, school, family, client

    private LocalDateTime creationDate;
}
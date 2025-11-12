package com.example.Contact.Manager.API.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ContactDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNo;
    private String email;
    private Boolean isActive;
    private String category;
    private LocalDateTime creationDate;
}
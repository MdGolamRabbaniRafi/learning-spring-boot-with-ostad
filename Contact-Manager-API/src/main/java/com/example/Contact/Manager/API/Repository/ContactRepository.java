package com.example.Contact.Manager.API.Repository;

import com.example.Contact.Manager.API.Entity.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    Page<Contact> findByFirstNameContainingIgnoreCase(String name, Pageable pageable);

    long countByIsActiveFalse();

    @Query("SELECT c.firstName AS firstName, c.email AS email FROM Contact c WHERE c.category = :category")
    Page<ContactProjection> findFirstNameAndEmailByCategory(@Param("category") String category, Pageable pageable);

    @Query("SELECT c FROM Contact c WHERE c.isActive = true")
    Page<Contact> findActiveContacts(Pageable pageable);

    @Query("SELECT c.firstName AS firstName, c.email AS email FROM Contact c WHERE c.isActive = true")
    Page<ContactProjection> findActiveContactsProjected(Pageable pageable);

    @Transactional
    @Modifying
    void deleteByPhoneNoStartingWith(String prefix);

    @Transactional
    @Modifying
    @Query("DELETE FROM Contact c WHERE c.phoneNo LIKE CONCAT(:prefix, '%')")
    void deleteByPhonePrefix(@Param("prefix") String prefix);
}
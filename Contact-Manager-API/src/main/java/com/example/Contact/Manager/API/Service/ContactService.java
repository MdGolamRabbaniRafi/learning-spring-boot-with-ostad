package com.example.Contact.Manager.API.Service;

import com.example.Contact.Manager.API.Entity.Contact;
import com.example.Contact.Manager.API.Repository.ContactProjection;
import com.example.Contact.Manager.API.Repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    public Contact save(Contact contact) {
        if (contact.getCreationDate() == null) contact.setCreationDate(LocalDateTime.now());
        return contactRepository.save(contact);
    }

    public List<Contact> saveAll(List<Contact> contacts) {
        contacts.forEach(c -> {
            if (c.getCreationDate() == null) c.setCreationDate(LocalDateTime.now());
        });
        return contactRepository.saveAll(contacts);
    }

    public Optional<Contact> findById(Long id){
        return contactRepository.findById(id);
    }

    public Page<Contact> searchByName(String name, Pageable pageable){
        return contactRepository.findByFirstNameContainingIgnoreCase(name, pageable);
    }


    public long countInactive(){
        return contactRepository.countByIsActiveFalse();
    }

    public Page<ContactProjection> findByCategory(String category, Pageable pageable){
        return contactRepository.findFirstNameAndEmailByCategory(category, pageable);
    }


    public Page<Contact> activeContacts(Pageable pageable){
        return contactRepository.findActiveContacts(pageable);
    }

    public Page<ContactProjection> activeContactsProjected(Pageable pageable){
        return contactRepository.findActiveContactsProjected(pageable);
    }

    @Transactional
    public void deleteByPhonePrefix(String prefix){
        contactRepository.deleteByPhonePrefix(prefix);
    }
}
package com.example.Contact.Manager.API.Controller;

import com.example.Contact.Manager.API.Entity.Contact;
import com.example.Contact.Manager.API.Repository.ContactProjection;
import com.example.Contact.Manager.API.Service.ContactService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contacts")
@RequiredArgsConstructor
public class ContactController {
    private final ContactService service;

    // 1. Create a new contact
    @PostMapping
    public ResponseEntity<Contact> create(@RequestBody Contact contact){
        Contact saved = service.save(contact);
        return ResponseEntity.ok(saved);
    }

    // 2. Get a contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable Long id){
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 3. Search contacts by first name (case-insensitive)
    @GetMapping("/search/name")
    public ResponseEntity<Page<Contact>> searchByName(
            @RequestParam String name,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<Contact> contacts = service.searchByName(name, pageable);
        return ResponseEntity.ok(contacts);
    }


    // 4. Count inactive contacts
    @GetMapping("/count-inactive")
    public ResponseEntity<Long> countInactive(){
        return ResponseEntity.ok(service.countInactive());
    }

    // 5. Get contacts by category (projection: firstName, email)
    @GetMapping("/search/category")
    public ResponseEntity<Page<ContactProjection>> byCategory(
            @RequestParam String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ContactProjection> contacts = service.findByCategory(category, pageable);
        return ResponseEntity.ok(contacts);
    }


    // 6. Retrieve all active contacts using pagination
    @GetMapping("/active/page")
    public ResponseEntity<Page<?>> activePage(@RequestParam(defaultValue = "0") int page,
                                              @RequestParam(defaultValue = "10") int size,
                                              @RequestParam(defaultValue = "false") boolean projected){
        Pageable pageable = PageRequest.of(page, size);
        if (projected) {
            Page<ContactProjection> p = service.activeContactsProjected(pageable);
            return ResponseEntity.ok(p);
        } else {
            Page<Contact> p = service.activeContacts(pageable);
            return ResponseEntity.ok(p);
        }
    }

    // 7. Delete all contacts whose phone number starts with a prefix
    @DeleteMapping("/delete-by-prefix")
    public ResponseEntity<Void> deleteByPrefix(@RequestParam String prefix){
        service.deleteByPhonePrefix(prefix);
        return ResponseEntity.noContent().build();
    }

    // 8. Add multiple contacts at once (bulk insert)
    @PostMapping("/mass")
    public ResponseEntity<List<Contact>> massInsert(@RequestBody List<Contact> contacts){
        List<Contact> saved = service.saveAll(contacts);
        return ResponseEntity.ok(saved);
    }

}

// 4.
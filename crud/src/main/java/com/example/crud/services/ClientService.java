package com.example.crud.services;

import com.example.crud.dto.ClientDTO;
import com.example.crud.entities.Client;
import com.example.crud.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    @Transactional
    public ClientDTO findById(Long id) {
        Optional<Client> obj = repository.findById(id);
        // Client entity = obj.get();
        Client entity = obj.orElseThrow(() -> new EntityNotFoundException("Can't find a Client corresponding the informed id: " + id));
        return new ClientDTO(entity);
    }

}

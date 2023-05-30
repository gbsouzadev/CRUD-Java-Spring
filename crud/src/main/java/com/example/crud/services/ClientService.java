package com.example.crud.services;

import com.example.crud.dto.ClientDTO;
import com.example.crud.entities.Client;
import com.example.crud.repositories.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository repository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Optional<Client> obj = repository.findById(id);
        // Client entity = obj.get();
        Client entity = obj.orElseThrow(() -> new EntityNotFoundException("Can't find a Client corresponding the informed id: " + id));
        return new ClientDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPagable(PageRequest pageRequest) {
        Page<Client> list = repository.findAll(pageRequest);
        return list.map(client -> new ClientDTO(client));
    }
}

package com.example.microlocal;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LocalService implements IServiceLocal {

    @Autowired
    private LocalRepository repository;


    @Override
    public List<Local> getAll() {
        return repository.findAll();
    }

    @Override
    public Local getById(String id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Local not found: " + id));
    }

    @Override
    public Local create(Local local) {
        if (local.getCreatedAt() == null) {
            local.setCreatedAt(Instant.now());
        }
        local.setId(null);  // MongoDB will generate the ID
        return repository.save(local);
    }

    @Override
    public Local update(String id, Local local) {

        Local existing = getById(id);

        existing.setName(local.getName());
        existing.setCity(local.getCity());
        existing.setAddress(local.getAddress());
        existing.setCapacity(local.getCapacity());
        existing.setType(local.getType());
        existing.setAvailable(local.isAvailable());
        existing.setPricePerHour(local.getPricePerHour());

        return repository.save(existing);
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Local not found: " + id);
        }
        repository.deleteById(id);
    }
    }


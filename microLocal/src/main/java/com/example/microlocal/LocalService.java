package com.example.microlocal;


import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsResource;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class LocalService implements IServiceLocal {

    public LocalService( GridFsTemplate gridFsTemplate /* + your other deps if any */) {

        this.gridFsTemplate = gridFsTemplate;
    }
    private final GridFsTemplate gridFsTemplate;
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
        existing.setLatitude(local.getLatitude());
        existing.setLongitude(local.getLongitude());
        // Note: imageUrl is updated separately via uploadImage endpoint

        return repository.save(existing);
    }

    @Override
    public void delete(String id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Local not found: " + id);
        }
        repository.deleteById(id);
    }



    public Local uploadImage(String localId, MultipartFile file) throws IOException {
        Local local = repository.findById(localId).orElseThrow();

        // delete previous file if exists
        if (local.getImageFileId() != null) {
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(new ObjectId(local.getImageFileId()))));
        }

        Document meta = new Document("localId", localId)
                .append("contentType", Optional.ofNullable(file.getContentType()).orElse("application/octet-stream"))
                .append("originalName", file.getOriginalFilename());

        ObjectId storedId = gridFsTemplate.store(file.getInputStream(),
                file.getOriginalFilename(), file.getContentType(), meta);

        local.setImageFileId(storedId.toHexString());
        // convenience URL for <img src>
        local.setImageUrl("/api/locals/" + localId + "/image");

        return repository.save(local);
    }

    // METHOD: build a streaming response for <img src="/api/locals/{id}/image">
    public ResponseEntity<InputStreamResource> streamImage(String localId) throws IOException {
        Local local = repository.findById(localId).orElseThrow();
        if (local.getImageFileId() == null) return ResponseEntity.notFound().build();

        GridFSFile gfile = gridFsTemplate.findOne(Query.query(Criteria.where("_id")
                .is(new ObjectId(local.getImageFileId()))));
        if (gfile == null) return ResponseEntity.notFound().build();

        GridFsResource resource = gridFsTemplate.getResource(gfile);
        String ctype = Optional.ofNullable(resource.getContentType()).orElse("application/octet-stream");

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(ctype))
                .cacheControl(CacheControl.noCache())
                .body(new InputStreamResource(resource.getInputStream()));
    }

    // METHOD: remove image
    public void deleteImage(String localId) {
        Local local = repository.findById(localId).orElseThrow();
        if (local.getImageFileId() != null) {
            gridFsTemplate.delete(Query.query(Criteria.where("_id").is(new ObjectId(local.getImageFileId()))));
            local.setImageFileId(null);
            local.setImageUrl(null);
            repository.save(local);
        }
    }

}


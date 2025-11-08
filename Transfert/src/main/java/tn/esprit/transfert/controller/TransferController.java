package tn.esprit.transfert.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.transfert.client.Club;
import tn.esprit.transfert.client.Joueur;
import tn.esprit.transfert.entity.Transfer;
import tn.esprit.transfert.entity.Offer;
import tn.esprit.transfert.service.TransferService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("transfers")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    // -------------------- TRANSFERS --------------------

    @GetMapping
    public ResponseEntity<List<Transfer>> getAllTransfers() {
        return ResponseEntity.ok(transferService.getAllTransfers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transfer> getTransferById(@PathVariable String id) {
        return transferService.getTransferById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createTransfer(@RequestBody Transfer transfer) {
        try {
            Transfer created = transferService.createTransfer(transfer);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTransfer(@PathVariable String id, @RequestBody Transfer transfer) {
        return transferService.updateTransfer(id, transfer)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransfer(@PathVariable String id) {
        transferService.deleteTransfer(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------- OFFERS --------------------

    @GetMapping("/offers")
    public ResponseEntity<List<Offer>> getAllOffers() {
        return ResponseEntity.ok(transferService.getAllOffers());
    }

    @GetMapping("/offers/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable String id) {
        return transferService.getAllOffers().stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/offers")
    public ResponseEntity<?> createOffer(@RequestBody Offer offer) {
        try {
            Offer created = transferService.createOffer(offer);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/offers/{id}")
    public ResponseEntity<?> updateOffer(@PathVariable String id, @RequestBody Offer offer) {
        return transferService.updateOffer(id, offer)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/offers/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable String id) {
        transferService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------- CLIENT INFO --------------------

    @GetMapping("/clubs")
    public List<Club> getAllClubs() {
        return transferService.getAllClubs();
    }

    @GetMapping("/clubs/{id}")
    public Optional<Club> getClubById(@PathVariable String id) {
        return transferService.getClubById(id);
    }

    @GetMapping("/joueurs/{id}")
    public ResponseEntity<Joueur> getJoueurs(@PathVariable String id) {
        return transferService.one(id);
    }
    @GetMapping("/joueurs")
    public List<Joueur> getAllJoueurs() {
        return transferService.all ();
    }

}

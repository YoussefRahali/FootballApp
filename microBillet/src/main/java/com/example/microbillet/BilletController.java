package com.example.microbillet;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billets")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // à ajuster selon ton front-end Angular
public class BilletController {

  private final BilletService billetService;

  /**
   * Crée un nouveau billet
   */
  @PostMapping
  public ResponseEntity<BilletResponse> createBillet(@Valid @RequestBody BilletRequest request) {
    BilletResponse created = billetService.createBillet(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);
  }

  /**
   * Récupère un billet par son ID
   */
  @GetMapping("/{id}")
  public ResponseEntity<BilletResponse> getBilletById(@PathVariable String id) {
    BilletResponse billet = billetService.getBilletById(id);
    return ResponseEntity.ok(billet);
  }

  /**
   * Liste tous les billets
   */
  @GetMapping
  public ResponseEntity<List<BilletResponse>> getAllBillets() {
    List<BilletResponse> list = billetService.listAll();
    return ResponseEntity.ok(list);
  }

  /**
   * Récupère les billets d’un match spécifique
   */
  @GetMapping("/match/{matchId}")
  public ResponseEntity<List<BilletResponse>> getBilletsByMatch(@PathVariable String matchId) {
    List<BilletResponse> billets = billetService.getBilletsByMatch(matchId);
    return ResponseEntity.ok(billets);
  }

  /**
   * Récupère les billets d’un acheteur spécifique
   */
  @GetMapping("/purchaser/{purchaserId}")
  public ResponseEntity<List<BilletResponse>> getBilletsByPurchaser(@PathVariable String purchaserId) {
    List<BilletResponse> billets = billetService.getBilletsByPurchaser(purchaserId);
    return ResponseEntity.ok(billets);
  }

  /**
   * Annule un billet
   */
  @PostMapping("/{id}/cancel")
  public ResponseEntity<BilletResponse> cancelBillet(@PathVariable String id) {
    BilletResponse updated = billetService.cancelBillet(id);
    return ResponseEntity.ok(updated);
  }

  /**
   * Marque un billet comme payé
   */
  @PostMapping("/{id}/pay")
  public ResponseEntity<BilletResponse> markBilletAsPaid(@PathVariable String id) {
    BilletResponse updated = billetService.markAsPaid(id);
    return ResponseEntity.ok(updated);
  }

  /**
   * Met à jour un billet existant
   */
  @PutMapping("/{id}")
  public ResponseEntity<BilletResponse> updateBillet(
      @PathVariable String id,
      @Valid @RequestBody BilletRequest request
  ) {
    BilletResponse updated = billetService.updateBillet(id, request);
    return ResponseEntity.ok(updated);
  }

  /**
   * Supprime un billet
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBillet(@PathVariable String id) {
    billetService.deleteBillet(id);
    return ResponseEntity.noContent().build();
  }
}


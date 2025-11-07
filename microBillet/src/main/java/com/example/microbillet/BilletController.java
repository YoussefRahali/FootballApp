package com.example.microbillet;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billets")

@CrossOrigin(origins = "*") // à ajuster selon ton front-end Angular
public class BilletController {
  private final BilletService billetService;

  public BilletController(BilletService billetService) {
    this.billetService = billetService;
  }

  @GetMapping("/{id}/details")
  public BilletWithMatchDTO getBilletDetails(@PathVariable String id) {
    return billetService.getBilletWithMatch(id);
  }

  @PostMapping
  public Billet createBillet(@RequestBody Billet billet) {
    return billetService.createBillet(billet);
  }






  @GetMapping("/{id}")
  public ResponseEntity<BilletResponse> getBilletById(@PathVariable String id) {
    BilletResponse billet = billetService.getBilletById(id);
    return ResponseEntity.ok(billet);
  }


  @GetMapping
  public ResponseEntity<List<BilletResponse>> getAllBillets() {
   List<BilletResponse> list = billetService.listAll();
    return ResponseEntity.ok(list);
  }

  @GetMapping("/match/{matchId}")
  public ResponseEntity<List<BilletResponse>> getBilletsByMatch(@PathVariable String matchId) {
    List<BilletResponse> billets = billetService.getBilletsByMatch(matchId);
    return ResponseEntity.ok(billets);
  }

  @GetMapping("/purchaser/{purchaserId}")
 public ResponseEntity<List<BilletResponse>> getBilletsByPurchaser(@PathVariable String purchaserId) {
    List<BilletResponse> billets = billetService.getBilletsByPurchaser(purchaserId);
    return ResponseEntity.ok(billets);
  }

  @PostMapping("/{id}/cancel")
  public ResponseEntity<BilletResponse> cancelBillet(@PathVariable String id) {
    BilletResponse updated = billetService.cancelBillet(id);
    return ResponseEntity.ok(updated);
  }


  @PostMapping("/{id}/pay")
 public ResponseEntity<BilletResponse> markBilletAsPaid(@PathVariable String id) {
    BilletResponse updated = billetService.markAsPaid(id);
   return ResponseEntity.ok(updated);
  }


  @PutMapping("/{id}")
  public ResponseEntity<BilletResponse> updateBillet(
      @PathVariable String id,
      @Valid @RequestBody BilletRequest request
  ) {
    BilletResponse updated = billetService.updateBillet(id, request);
    return ResponseEntity.ok(updated);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBillet(@PathVariable String id) {
    billetService.deleteBillet(id);
    return ResponseEntity.noContent().build();
 }
}


package com.example.microbillet;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class BilletService {
  private final BilletRepository repository;
  private final MatchFeignClient matchFeignClient;

  public BilletService(BilletRepository billetRepository, MatchFeignClient matchFeignClient) {
    this.repository = billetRepository;
    this.matchFeignClient = matchFeignClient;
  }

  public BilletWithMatchDTO getBilletWithMatch(String billetId) {
    Billet billet = repository.findById(billetId)
        .orElseThrow(() -> new RuntimeException("Billet not found"));

    // ✅ Fetch match data via Feign client
    MatchDTO match = matchFeignClient.getMatchById(billet.getMatchId());

    // Combine into one DTO
    BilletWithMatchDTO dto = new BilletWithMatchDTO();
    dto.setId(billet.getId());
    dto.setNumero(billet.getNumero());
    dto.setPrix(billet.getPrix());
    dto.setMatch(match);

    return dto;
  }
  public Billet createBillet(Billet billet) {
    return repository.save(billet);
  }


  public BilletResponse getBilletById(String id) {
    Billet b = repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Billet not found with id: " + id));
    return BilletMapper.toResponse(b);
  }


  public List<BilletResponse> getBilletsByMatch(String matchId) {
    List<Billet> list = repository.findByMatchId(matchId);
    return list.stream().map(BilletMapper::toResponse).collect(Collectors.toList());
  }


  public List<BilletResponse> getBilletsByPurchaser(String purchaserId) {
    List<Billet> list = repository.findByPurchaserId(purchaserId);
    return list.stream().map(BilletMapper::toResponse).collect(Collectors.toList());
  }


  public List<BilletResponse> listAll() {
    return repository.findAll().stream().map(BilletMapper::toResponse).collect(Collectors.toList());
  }


  @Transactional
  public BilletResponse cancelBillet(String id) {
    Billet b = repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Billet not found with id: " + id));

    if ("CANCELLED".equalsIgnoreCase(b.getStatus())) {
      // already cancelled — return as is
      return BilletMapper.toResponse(b);
    }

    b.setStatus("CANCELLED");
    b.setUpdatedAt(Instant.now());
    Billet updated = repository.save(b);
    return BilletMapper.toResponse(updated);
  }


  @Transactional
  public BilletResponse markAsPaid(String id) {
    Billet b = repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Billet not found with id: " + id));

    if ("PAID".equalsIgnoreCase(b.getStatus())) {
      return BilletMapper.toResponse(b);
    }

    b.setStatus("PAID");
    b.setUpdatedAt(Instant.now());
    Billet updated = repository.save(b);
    return BilletMapper.toResponse(updated);
  }


  @Transactional
  public BilletResponse updateBillet(String id, BilletRequest request) {
    Billet existing = repository.findById(id)
        .orElseThrow(() -> new NotFoundException("Billet not found with id: " + id));

    // update allowed fields (not id, not createdAt)
    existing.setMatchId(request.getMatchId());
    existing.setPurchaserId(request.getPurchaserId());
    existing.setSeat(request.getSeat());
    existing.setPrice(request.getPrice() != null ? request.getPrice() : existing.getPrice());
    existing.setUpdatedAt(Instant.now());

    Billet saved = repository.save(existing);
    return BilletMapper.toResponse(saved);
  }


  public void deleteBillet(String id) {
    boolean exists = repository.existsById(id);
    if (!exists) throw new NotFoundException("Billet not found with id: " + id);
    repository.deleteById(id);
  }
}

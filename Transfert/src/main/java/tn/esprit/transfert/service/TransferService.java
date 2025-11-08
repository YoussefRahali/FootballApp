package tn.esprit.transfert.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import tn.esprit.transfert.client.Club;
import tn.esprit.transfert.client.ClubClient;
import tn.esprit.transfert.client.Joueur;
import tn.esprit.transfert.client.JoueurClient;
import tn.esprit.transfert.entity.Transfer;
import tn.esprit.transfert.entity.Offer;
import tn.esprit.transfert.repository.TransferRepository;
import tn.esprit.transfert.repository.OfferRepository;
import feign.FeignException;

import java.util.List;
import java.util.Optional;

@Service
public class TransferService {

    private final TransferRepository transferRepository;
    private final OfferRepository offerRepository;
//    private final JoueurClient joueurClient;

    public TransferService(TransferRepository transferRepository,
                           OfferRepository offerRepository,
                           ClubClient clubClient
                           ) {
        this.transferRepository = transferRepository;
        this.offerRepository = offerRepository;
        this.clubClient = clubClient;
        //this.joueurClient = joueurClient;
    }

    // ---------------- TRANSFERS ----------------
    public List<Transfer> getAllTransfers() {
        return transferRepository.findAll();
    }

    public Optional<Transfer> getTransferById(String id) {
        return transferRepository.findById(id);
    }

    public Transfer createTransfer(Transfer transfer) {
        //Joueur joueur = getJoueurSafe(transfer.getJoueurId());
        //Club fromClub = getClubSafe(transfer.getFromClubId());
        //Club toClub = getClubSafe(transfer.getToClubId());

        return transferRepository.save(transfer);
    }

    public Optional<Transfer> updateTransfer(String id, Transfer details) {
        return transferRepository.findById(id)
                .map(t -> {
                    t.setJoueurId(details.getJoueurId());
                    t.setFromClubId(details.getFromClubId());
                    t.setToClubId(details.getToClubId());
                    t.setTransferFee(details.getTransferFee());
                    t.setTransferDate(details.getTransferDate());
                    return transferRepository.save(t);
                });
    }

    public void deleteTransfer(String id) {
        transferRepository.deleteById(id);
    }

    // ---------------- OFFERS ----------------
    public List<Offer> getAllOffers() {
        return offerRepository.findAll();
    }

    public Offer createOffer(Offer offer) {
        //Joueur joueur = getJoueurSafe(offer.getJoueurId());
        //Club club = getClubSafe(offer.getClubId());

        return offerRepository.save(offer);
    }

    public Optional<Offer> updateOffer(String id, Offer details) {
        return offerRepository.findById(id)
                .map(o -> {
                    o.setJoueurId(details.getJoueurId());
                    o.setClubId(details.getClubId());
                    o.setAmount(details.getAmount());
                    o.setOfferDate(details.getOfferDate());
                    o.setStatus(details.getStatus());
                    return offerRepository.save(o);
                });
    }

    public void deleteOffer(String id) {
        offerRepository.deleteById(id);
    }

    // ---------------- CLIENT INFO ----------------
    public Optional<Club> getClubInfo(String clubId) {
        return getClubSafe(clubId);
    }

    public Joueur getJoueurInfo(String joueurId) {
        return getJoueurSafe(joueurId);
    }

    // ---------------- UTILITAIRES ----------------
    private Joueur getJoueurSafe(String joueurId) {
        try {
            Joueur joueur = joueurClient.one(joueurId).getBody ();
            if (joueur == null || "Microservice Joueur indisponible".equals(joueur.getNom())) {
                throw new RuntimeException("Joueur indisponible ou inexistant");
            }
            return joueur;
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Joueur avec ID " + joueurId + " non trouvé");
        } catch (Exception e) {
            throw new RuntimeException("Impossible de contacter le microservice Joueur : " + e.getMessage());
        }
    }

    private Optional<Club> getClubSafe(String clubId) {
        try {
            Optional<Club> club = clubClient.getClubById(clubId);
            return club;
        } catch (FeignException.NotFound e) {
            throw new RuntimeException("Club avec ID " + clubId + " non trouvé");
        } catch (Exception e) {
            throw new RuntimeException("Impossible de contacter le microservice Club : " + e.getMessage());
        }
    }



    @Autowired
    private ClubClient clubClient;

    public List<Club> getAllClubs(){
        return clubClient.getAllClubs();
    }

    public Optional<Club> getClubById(String id){
        return clubClient.getClubById(id);
    }

    @Autowired
    private JoueurClient joueurClient;

    public List<Joueur> all() {
        return joueurClient.all();
    }
    public ResponseEntity<Joueur> one(@PathVariable String id) {
        return joueurClient.one(id);
    }
}

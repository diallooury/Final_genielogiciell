package com.example.demo1.Controller;
import ServiceCompte.CompteService;
import com.example.demo1.Model.Comptes;
import com.example.demo1.Model.Transaction;
import com.example.demo1.Reopsitory.CompteRepository;
import com.example.demo1.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.math.BigDecimal;

@RestController
@RequestMapping("/comptes")
public class CompteController {
    @Autowired
    private CompteService compteService;
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private TransactionService transactionService;
    @PostMapping("")
    public ResponseEntity<Comptes> createCompte(@RequestBody Comptes compte) {
        Comptes createdCompte = compteRepository.save(compte);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCompte);
    }

    @GetMapping("")
    public List<Comptes> getAllComptes() {
        return compteRepository.findAll();
    }

    @GetMapping("/{id}")
    public Comptes getCompteById(@PathVariable Long id) {
        return compteRepository.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Comptes updateCompte(@PathVariable Long id, @RequestBody Comptes updatedCompte) {
        Comptes existingCompte = compteRepository.findById(id).orElse(null);

        if (existingCompte != null) {
            existingCompte.setOwner(updatedCompte.getOwner());
            existingCompte.setSolde(updatedCompte.getSolde());
            return compteRepository.save(existingCompte);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteComptes(@PathVariable Long id) {
        compteRepository.deleteById(id);
    }


    @PostMapping("/transfer")
    public ResponseEntity<String> transferFunds(@RequestParam Long fromAccountId, @RequestParam Long toAccountId, @RequestParam BigDecimal amount) {
        Comptes fromAccount = compteRepository.findById(fromAccountId).orElse(null);
        Comptes toAccount = compteRepository.findById(toAccountId).orElse(null);

        if (fromAccount == null || toAccount == null) {
            return ResponseEntity.badRequest().body("Compte(s) invalide(s)");
        }

        if (fromAccount.getSolde().compareTo(amount) < 0) {
            return ResponseEntity.badRequest().body("Solde insuffisant pour effectuer le transfert");
        }

        fromAccount.setSolde(fromAccount.getSolde().subtract(amount));
        toAccount.setSolde(toAccount.getSolde().add(amount));

        compteRepository.save(fromAccount);
        compteRepository.save(toAccount);
        Transaction transaction = transactionService.createTransaction(fromAccountId, toAccountId, amount);

        return ResponseEntity.ok("Transfert réussi");
    }


    @PostMapping("/{id}/depot")
    public ResponseEntity<String> effectuerDepot(@PathVariable Long id, @RequestParam BigDecimal montant) {
        Comptes compte = compteRepository.findById(id).orElse(null);

        if (compte != null) {
            compteService.depot(compte, montant);
            return ResponseEntity.ok("Dépôt effectué avec succès. Nouveau solde : " + compte.getSolde());
        } else {
            return ResponseEntity.badRequest().body("Compte non trouvé.");
        }
    }

    @PostMapping("/{id}/retrait")
    public ResponseEntity<String> effectuerRetrait(@PathVariable Long id, @RequestParam BigDecimal montant) {
        Comptes compte = compteRepository.findById(id).orElse(null);

        if (compte != null) {
            try {
                compteService.retirer(compte, montant);
                return ResponseEntity.ok("Retrait effectué avec succès. Nouveau solde : " + compte.getSolde());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        } else {
            return ResponseEntity.badRequest().body("Compte non trouvé.");
        }
    }



}










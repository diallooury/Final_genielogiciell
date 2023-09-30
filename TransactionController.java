package com.example.demo1.Controller;
import com.example.demo1.TransactionService;
import com.example.demo1.Model.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo1.Reopsitory.TransactionRepository;


import java.util.List;
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController( TransactionService transactionService) {

        this.transactionService = transactionService;
    }

    @GetMapping("")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
//    @PostMapping("/cancel/{transactionId}")
//    public ResponseEntity<String> cancelTransaction(@PathVariable Long transactionId) {
//        // Récupérer la transaction à annuler depuis la base de données
//        Transaction transactionToCancel = transactionService.getTransactionById(transactionId);
//
//        if (transactionToCancel == null) {
//            return ResponseEntity.badRequest().body("Transaction non trouvée");
//        }
//
//        // Effectuer l'annulation de la transaction (mettre en œuvre la logique d'annulation appropriée)
//        boolean cancellationSuccess = transactionService.cancelTransaction(transactionToCancel);
//
//        if (cancellationSuccess) {
//            return ResponseEntity.ok("Transaction annulée avec succès");
//        } else {
//            return ResponseEntity.badRequest().body("Impossible d'annuler la transaction");
//        }
//    }
}

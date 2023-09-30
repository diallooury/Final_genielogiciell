package com.example.demo1;

import com.example.demo1.Controller.TransactionCreatedEvent;
import com.example.demo1.Model.Comptes;
import com.example.demo1.Model.Transaction;
import com.example.demo1.Reopsitory.CompteRepository;
import com.example.demo1.Reopsitory.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    private final ApplicationEventPublisher eventPublisher;
    private final TransactionRepository transactionRepository;
    private final CompteRepository compteRepository;

    @Autowired
    public TransactionService(ApplicationEventPublisher eventPublisher, TransactionRepository transactionRepository, CompteRepository compteRepository) {
        this.eventPublisher = eventPublisher;
        this.transactionRepository = transactionRepository;
        this.compteRepository = compteRepository;
    }

    public Transaction createTransaction(Long fromAccountId, Long toAccountId, BigDecimal amount) {
        // ...

        // Créez une nouvelle transaction avec les détails appropriés
        Transaction transaction = new Transaction();
        transaction.setAccountIdFrom(fromAccountId);
        transaction.setAccountIdTo(toAccountId);
        transaction.setAmount(amount);
        transaction.setTimestamp(LocalDateTime.now());

        // Enregistrez la transaction dans la table des transactions
        transactionRepository.save(transaction);

        // Émettez l'événement TransactionCreatedEvent
        eventPublisher.publishEvent(new TransactionCreatedEvent(this, transaction));

        // Mettez à jour les soldes des comptes
        // ...

        return transaction;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    // ...

//    public Transaction createCancellation(Long transactionIdToCancel) {
//        Transaction originalTransaction = transactionRepository.findById(transactionIdToCancel).orElse(null);
//
//        if (originalTransaction == null || originalTransaction.getTransactionType().equals("CANCELLATION")) {
//            throw new IllegalArgumentException("Transaction invalide à annuler.");
//        }
//
//        // Créez une nouvelle transaction d'annulation
//        Transaction cancellationTransaction = new Transaction();
//        cancellationTransaction.setAccountIdFrom(originalTransaction.getAccountIdTo());
//        cancellationTransaction.setAccountIdTo(originalTransaction.getAccountIdFrom());
//        cancellationTransaction.setAmount(originalTransaction.getAmount().negate()); // Montant inverse
//        cancellationTransaction.setTransactionType("CANCELLATION");
//        cancellationTransaction.setTimestamp(LocalDateTime.now());
//        cancellationTransaction.setTransactionIdToCancel(originalTransaction.getId());
//
//        // Enregistrez la transaction d'annulation dans la table des transactions
//        transactionRepository.save(cancellationTransaction);
//
//        // Mettez à jour les soldes des comptes en conséquence (opération inverse)
//        Comptes fromAccount = compteRepository.findById(originalTransaction.getAccountIdTo()).orElse(null);
//        Comptes toAccount = compteRepository.findById(originalTransaction.getAccountIdFrom()).orElse(null);
//
//        if (fromAccount != null && toAccount != null) {
//            fromAccount.setSolde(fromAccount.getSolde().subtract(cancellationTransaction.getAmount()));
//            toAccount.setSolde(toAccount.getSolde().add(cancellationTransaction.getAmount()));
//            compteRepository.save(fromAccount);
//            compteRepository.save(toAccount);
//        }
//
//        return cancellationTransaction;
//    }

    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId).orElse(null);
    }

//    public boolean cancelTransaction(Transaction transactionToCancel) {
//        // Vérifiez si la transaction n'est pas déjà annulée
//        if (transactionToCancel.isCancelled()) {
//            return false;
//        }
//
//        // Marquez la transaction comme annulée
//        transactionToCancel.setCancelled(true);
//        transactionRepository.save(transactionToCancel);
//
//        // Rétablissez les soldes des comptes en conséquence (opération inverse)
//        Comptes fromAccount = compteRepository.findById(transactionToCancel.getAccountIdFrom()).orElse(null);
//        Comptes toAccount = compteRepository.findById(transactionToCancel.getAccountIdTo()).orElse(null);
//
//        if (fromAccount != null && toAccount != null) {
//            fromAccount.setSolde(fromAccount.getSolde().add(transactionToCancel.getAmount()));
//            toAccount.setSolde(toAccount.getSolde().subtract(transactionToCancel.getAmount()));
//            compteRepository.save(fromAccount);
//            compteRepository.save(toAccount);
//        }
//
//        return true;
//    }


} // La transaction est déjà annulée





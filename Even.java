package com.example.demo1.Controller;

import com.example.demo1.Model.Transaction;
import com.example.demo1.Reopsitory.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class Even implements ApplicationListener<TransactionCreatedEvent> {

    private final TransactionRepository transactionRepository;

    @Autowired
//    public  void Even (TransactionRepository transactionRepository) {
//        this.transactionRepository = transactionRepository;
//    }

    public Even(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public void onApplicationEvent(TransactionCreatedEvent event) {
        // Récupérer les détails de la transaction à partir de l'événement
        Transaction transaction = event.getTransaction();

        // Enregistrer la transaction dans la base de données
        transactionRepository.save(transaction);
    }
}

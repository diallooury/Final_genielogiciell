package com.example.demo1.Reopsitory;

import com.example.demo1.Model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository   extends JpaRepository<Transaction ,Long> {
}

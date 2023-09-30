package com.example.demo1.Model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_id_from")
    private Long accountIdFrom;

    @Column(name = "account_id_to")
    private Long accountIdTo;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

//    @Column(name = "cancelled")
//    private boolean cancelled = false; // Initialise la propriété cancelled avec la valeur par défaut false
//    // Utilisez Boolean au lieu de boolean
//
//
//
//    public boolean isCancelled() {
//        return cancelled;
//    }
//
//
//    public void setCancelled(Boolean cancelled) {
//        this.cancelled = cancelled;
//    }
//    // Initialise la propriété cancelled avec la valeur par défaut false
//
//    private String transactionType;
//
//    private Long transactionIdToCancel;
//
//    // Constructeurs, getters et setters
//
//    public Transaction() {
//        this.cancelled = false; // Initialise la propriété cancelled avec la valeur par défaut false
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountIdFrom() {
        return accountIdFrom;
    }

    public void setAccountIdFrom(Long accountIdFrom) {
        this.accountIdFrom = accountIdFrom;
    }

    public Long getAccountIdTo() {
        return accountIdTo;
    }

    public void setAccountIdTo(Long accountIdTo) {
        this.accountIdTo = accountIdTo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

//    public String getTransactionType() {
//        return this.transactionType;
//    }
//
//    public void setTransactionType(String cancellation) {
//        this.transactionType = "ANNULATION";
//    }
//
//    public Long getTransactionIdToCancel() {
//        return transactionIdToCancel;
//    }
//
//    public void setTransactionIdToCancel(Long id) {
//        this.transactionIdToCancel = id;
//    }

//    public boolean isCancelled() {
//        return cancelled;
//    }

//    public void setCancelled(boolean cancelled) {
//        this.cancelled = cancelled;
//    }
}

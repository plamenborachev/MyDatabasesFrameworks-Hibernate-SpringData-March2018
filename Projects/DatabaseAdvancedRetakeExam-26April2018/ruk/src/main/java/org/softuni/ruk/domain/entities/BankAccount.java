package org.softuni.ruk.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "account_number", nullable = false)
    private String accountNumber;

    @Column
    private BigDecimal balance;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Client client;

    @OneToMany(mappedBy = "bankAccount", targetEntity = Card.class)
    private Set<Card> cards;

    public BankAccount() {
        this.cards = new HashSet<>();
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return this.balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Client getClient() {
        return this.client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Set<Card> getCards() {
        return this.cards;
    }

    public void setCards(Set<Card> cards) {
        this.cards = cards;
    }
}

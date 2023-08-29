package nymble.assignment.entity;

import java.util.HashSet;
import java.util.Set;
/*
* Account Entinty which each passenger will hold
* Account Entity has balance and transactions
* */
public class Account {
    Double balance;
    Set<Transaction> transactions;

    public Account(Double balance) {
        this.balance = balance;
        this.transactions = new HashSet<>();
    }

    public Double getBalance() {
        return balance;
    }

    public Set<Transaction> getTransactions() {
        return transactions;
    }

    public void purchaseOrder(Double cost, String purpose){
        Transaction transaction = new Transaction(cost, purpose);
        balance = balance - cost;
        transactions.add(transaction);
    }

}

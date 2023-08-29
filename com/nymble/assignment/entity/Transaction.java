package nymble.assignment.entity;

import com.nymble.assignment.utility.NumberGenerator;
/*
* Transaction Entity for every purchase of user
* */
public class Transaction {
    Integer transactionId;
    Double transactionAmount;
    String transactionDetail;

    public Transaction(Double transactionAmount, String transactionDetail) {
        this.transactionAmount = transactionAmount;
        this.transactionDetail = transactionDetail;
        this.transactionId = NumberGenerator.getNextTransactionNumber();
    }

    @Override
    public String toString() {
        return "---- Transaction ----\n" +
                "transactionId:" + transactionId +
                "\ntransactionAmount:" + transactionAmount +
                "\ntransactionDetail:'" + transactionDetail;
    }
}

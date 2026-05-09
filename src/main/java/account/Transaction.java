package account;

import service.BankOperation;

import java.util.Date;

/**
 * @author Tuan Hiep TRAN
 */
public class Transaction {
    private Double currentBalance;
    private Double nextBalance;
    private Double value;
    private Date date;
    private String time;
    private BankOperation typeOperation;
    private String destination;
    private String comment;

    public Transaction(Double current, Double value, Date date, BankOperation typeOperation) {
        this.currentBalance = current;
        this.value = value;
        this.date = date;
        this.typeOperation = typeOperation;
    }

    public Transaction(Double current, Double value, Date date, String time, BankOperation typeOperation, String destination, String comment) {
        this.currentBalance = current;
        this.value = value;
        this.date = date;
        this.time = time;
        this.typeOperation = typeOperation;
        this.destination = destination;
        this.comment = comment;
    }

    public void operate() {

        switch (typeOperation) {

            case CREDIT:
                this.nextBalance = currentBalance + value;
                break;
            case DEBIT:
                this.nextBalance = currentBalance - value;
                break;
            default:
                throw new AssertionError("Unknown operation ");
        }
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BankOperation getTypeOperation() {
        return typeOperation;
    }

    public void setTypeOperation(BankOperation typeOperation) {
        this.typeOperation = typeOperation;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Double getNextBalance() {
        return nextBalance;
    }

    public void setNextBalance(Double nextBalance) {
        this.nextBalance = nextBalance;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

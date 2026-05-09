package account;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import service.BankOperation;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Tuan Hiep TRAN
 */
public class Account {
    private static AtomicInteger ID_COUNT = new AtomicInteger();
    private int id;
    private String name;
    private final double INITIAL_BALANCE = 0.0000;
    private static final String DATE_FORMAT = "dd/MM/yyyy";
    private SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    private Double balance = INITIAL_BALANCE;
    private List<Transaction> transactions = new ArrayList<Transaction>();

    public Account() {
        this.id = ID_COUNT.incrementAndGet();
    }

    public Account(String name, Double balance) {
        this.id = ID_COUNT.incrementAndGet();
        this.name = name;
        this.balance = balance;
    }

    public void credit(Double value, Date date) {
        doTransaction(value, date, BankOperation.CREDIT);
    }

    public void debit(Double value, Date date) {
        doTransaction(value, date, BankOperation.DEBIT);
    }

    private void doTransaction(Double value, Date date, BankOperation typeOperation) {
        Transaction transaction = new Transaction(balance, value, date, typeOperation);
        transaction.operate();
        transactions.add(transaction);
        balance = transaction.getNextBalance();
    }


    // with specific time

    public void credit(Double value, Date date, String time, String destination, String comment) {
        doTransaction(value, date, time, BankOperation.CREDIT, destination, comment);
    }

    public void debit(Double value, Date date, String time, String destination, String comment) {
        doTransaction(value, date, time, BankOperation.DEBIT, destination, comment);
    }

    private void doTransaction(Double value, Date date, String time, BankOperation typeOperation, String destination, String comment) {
        Transaction transaction = new Transaction(balance, value, date, time, typeOperation, destination, comment);
        transaction.operate();
        transactions.add(transaction);
        balance = transaction.getNextBalance();
    }


    public Double getBalance() {
        return balance;
    }

    public void printBalanceHistory(PrintStream printer) {

        StringBuilder builder = new StringBuilder();
        builder.append("KATA BANK Total Balance " + balance);
        builder.append(System.getProperty("line.separator"));
        builder.append("Operation History ");
        builder.append(System.getProperty("line.separator"));

        for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext(); ) {
            Transaction transaction = (Transaction) iterator.next();
            // Add history of each transaction
            builder.append("Previous balance: ")
                    .append(StringUtils.rightPad(transaction.getCurrentBalance().toString(), 10))
                    .append(" | ")
                    .append(sdf.format(transaction.getDate()))
                    .append(" | ")
                    .append(transaction.getTypeOperation().toString())
                    .append(" ")
                    .append(transaction.getValue().toString())
                    .append(" | ")
                    .append("Balance ")
                    .append(StringUtils.rightPad(transaction.getNextBalance().toString(), 10));

            builder.append(System.getProperty("line.separator"));

        }
        printer.println(builder.toString());

    }

    public void printBalanceByDate(PrintStream printer, Date date) {
        StringBuilder builder = new StringBuilder();
        builder.append("KATA BANK Balance at " + date.toString());
        builder.append(System.getProperty("line.separator"));

        for (Iterator<Transaction> iterator = transactions.iterator(); iterator.hasNext(); ) {
            Transaction transaction = (Transaction) iterator.next();
            if (date.compareTo(transaction.getDate()) != 0) {
                continue;
            }
            // Add history of each transaction
            builder.append("Previous balance: ")
                    .append(StringUtils.rightPad(transaction.getCurrentBalance().toString(), 10))
                    .append(" | ")
                    .append(sdf.format(transaction.getDate()))
                    .append(" | ")
                    .append(transaction.getTime())
                    .append(" | ")
                    .append(transaction.getTypeOperation().toString())
                    .append(" ")
                    .append(transaction.getValue().toString())
                    .append(" | ")
                    .append("Balance ")
                    .append(StringUtils.rightPad(transaction.getNextBalance().toString(), 10))
                    .append(" | ")
                    .append("to: ")
                    .append(StringUtils.rightPad(transaction.getDestination(), 10))
                    .append(" | ")
                    .append("Comment: ")
                    .append(StringUtils.rightPad(transaction.getComment(), 10));


            builder.append(System.getProperty("line.separator"));

        }
        printer.println(builder.toString());
    }


    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public void doBulkTransactions(String filePath) throws IOException, ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(Paths.get(filePath).toFile(), HashMap.class);
        ArrayList<HashMap> transactions = (ArrayList) map.get("transactions");
        // print map entries
        for (HashMap transaction : transactions) {
            String type = (String) transaction.get("type");
            Double amount = (Double) transaction.get("amount");
            String date = (String) transaction.get("date");
            String time = (String) transaction.get("time");
            String destination = (String) transaction.get("to");
            String comment = (String) transaction.get("comment");
            if ("debit".equals(type)) {
                this.debit(amount, dateFormat.parse(date), time, destination, comment);
            }
            if ("credit".equals(type)) {
                this.credit(amount, dateFormat.parse(date), time, destination, comment);
            }
        }

    }
}

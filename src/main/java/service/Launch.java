package service;
import account.Account;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @author Tuan Hiep TRAN
 */
public class Launch {

    public static void main(String[] args) throws ParseException, IOException {
        System.out.println("WELCOME TO TALAN - KATA BANK");
        /* Initialize an account and do some operations */
        Account account = new Account();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        account.credit(1000.00, dateFormat.parse("2021-01-01"));
        account.credit(2000.00, dateFormat.parse("2021-01-02"));
        account.debit(500.00, dateFormat.parse("2021-01-03"));
        /*
        1.	Search for past transaction as well as future transactions
        2.	Future transactions will not be considered for the balance
        */
        System.out.println("* Retrieving all transaction and balance history ..");
        account.printBalanceHistory(System.out);
        /*
        4.Creating transactions in bulk from the payload in json file
         */
        System.out.println("* Creating transactions in bulk from the payload in json file ...");
        account.doBulkTransactions("src/test/resources/payload/transactions.json");
        account.printBalanceHistory(System.out);
        /*
        3.Provide the balance in the account for a provided date
         */
        System.out.println("* Print out the balance of provide date: ");
        account.printBalanceByDate(System.out, dateFormat.parse("2021-01-09"));
    }


}

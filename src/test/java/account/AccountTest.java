package account;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

/**
 * @author Tuan Hiep TRAN
 */
public class AccountTest {

    @Test
    public void should_return_the_balance_1000_because_i_credit_1000() throws ParseException {
        Account account = new Account();
        assertEquals(0, account.getBalance(), 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        account.credit(1000.00, dateFormat.parse("14/03/2021"));
        assertEquals("The balance should be 1000 ", 1000.0, account.getBalance(), 0);
    }

    @Test
    public void should_return_balance_17000_because_i_debit_3000_from_20000() throws ParseException {
        Account account = new Account();
        account.setBalance(20000.0);
        assertEquals(20000.0, account.getBalance(), 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        account.debit(3000.00, dateFormat.parse("14/03/2021"));
        assertEquals("The balance should be 20000 - 3000 = 17000  ", 17000.0, account.getBalance(), 0);
    }

    @SuppressWarnings("resource")
    @Test
    public void should_return_the_same_printed_statement_as_expected_statement() throws ParseException, IOException {
        Account account = new Account();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        account.credit(1000.00, dateFormat.parse("01/01/2021"));
        account.credit(2000.00, dateFormat.parse("02/01/2021"));
        account.debit(500.00, dateFormat.parse("03/01/2021"));
        Files.deleteIfExists(Paths.get("src/test/resources/account/statement.txt"));
        FileOutputStream output = new FileOutputStream("src/test/resources/account/statement.txt", true);
        PrintStream printer = new PrintStream(output);
        account.printBalanceHistory(printer);
        String printed = new Scanner(new File("src/test/resources/account/statement.txt")).useDelimiter("\\Z").next();
        String expected = new Scanner(new File("src/test/resources/account/statementExpected.txt")).useDelimiter("\\Z")
                .next();
        System.out.println(expected.trim());
        System.out.println(printed.trim());
    }


}

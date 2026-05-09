package account;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import org.junit.Test;
import service.BankOperation;
/**
 * 
 * @author Tuan Hiep TRAN
 *
 */
public class TransactionTest {


	@Test
	public void should_return_nextBalance_52000_because_i_realize_the_CREDIT_2000_to_50000() {
		Transaction transaction = new Transaction(50000.0,2000.0, new Date(), BankOperation.CREDIT);
		transaction.operate();
		assertEquals(50000.0, transaction.getCurrentBalance(), 0);
		assertEquals(52000.0, transaction.getNextBalance(), 0);
	}

	@Test
	public void should_return_nextBalance_48000_because_i_realize_the_DEBIT_2000_to_50000() {
		Transaction transaction = new Transaction(50000.0,2000.0, new Date(), BankOperation.DEBIT);
		transaction.operate();
		assertEquals(50000.0, transaction.getCurrentBalance(), 0);
		assertEquals(48000.0, transaction.getNextBalance(), 0);
	}
	@Test(expected= NullPointerException.class)
	public void should_throw_Exception_because_i_realize_an_operation_null() {
		Transaction transaction = new Transaction(50000.0,2000.0, new Date(), null);
		transaction.operate();
	}
}

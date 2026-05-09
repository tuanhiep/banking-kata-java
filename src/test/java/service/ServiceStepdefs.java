package service;


import static org.junit.Assert.assertEquals;

import java.util.Date;

import account.Account;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * 
 * @author Tuan Hiep TRAN
 *
 */
public final class ServiceStepdefs {

    private Account account = new Account();
    
    /**
     * i_credit.
     *
     * @param credit
     */
    @Given("^I credit (\\d+) euros$")
    public void i_credit(double credit) {
        account.credit(credit, new Date());
    }
    
    /**
     * i_debit.
     *
     * @param credit
     */
    @Given("^I debit (\\d+) euros$")
    public void i_debit(double credit) {
        account.debit(credit, new Date());
    }
    
    /**
     * i_print_the_statement.
     *
     */
    @When("^I print the statement$")
    public void i_print_the_statement() {
        account.printBalanceHistory(System.out);
    }
    
    /**
     * my_balance_should_be.
     *
     * @param expectedBalance
     */
    @Then("^My balance should be (-?\\d+)")
    public void my_balance_should_be(double expectedBalance) {
        assertEquals(expectedBalance, account.getBalance(), 0);
    }
    
}

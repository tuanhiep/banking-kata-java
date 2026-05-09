Feature: Bank Account 

Scenario: Deposit 1000 euros and Print the statement balance 
	Given I credit 1000 euros
	When I print the statement 
	Then My balance should be 1000 
	
Scenario: Deposit and Withdraw and Print the statement balance 
	Given I credit 1000 euros
	And I credit 2000 euros
	And I debit 500 euros
	When I print the statement 
	Then My balance should be 2500 
	
Scenario: I debit 1500 and Print the statement balance
	Given I debit 1500 euros
	When I print the statement 
	Then My balance should be -1500 
    

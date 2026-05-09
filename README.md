# Banking Kata Java

Java kata for practicing banking-domain behavior with BDD/TDD style tests.

## Purpose

This repository is a learning kata. It is useful for practicing domain modeling, test-driven development, and behavior-driven scenarios in a small banking context.

## Example Scenarios

- Deposit money and print the account statement.
- Deposit, withdraw, and verify the final balance.
- Withdraw into a negative balance and verify the behavior.

## How To Use

Run the test suite from the IDE or command line.

```bash
mvn test
```

## Status

Learning kata. The next upgrade is to clarify the domain rules, add example outputs, and modernize dependencies if this repo becomes active again.

## Validation

The core validation path is the behavior test suite. Add scenarios whenever a new business rule is introduced.

## Engineering Notes

The important engineering signal is not the framework choice; it is whether the domain language, examples, and tests make the banking rules unambiguous.

package com.cerotid.bank.ui;

import java.util.*;

import com.cerotid.bank.bo.BankBO;
import com.cerotid.bank.bo.BankBOImpl;
import com.cerotid.bank.model.Account;
import com.cerotid.bank.model.AccountType;
import com.cerotid.bank.model.Address;
import com.cerotid.bank.model.Customer;
import com.cerotid.bank.model.Transaction;

public class BankUI {
	// TO create singleton implementation
	// private static final Double COUNT = null;
	private BankBO bankBo;

	private void setBankBo(BankBO bankBo) {
		this.bankBo = bankBo;
	}

	public BankBO getBankBo() {
		return bankBo;
	}

	public static void main(String[] args) {
		BankUI bankUI = new BankUI();
		bankUI.setBankBo(new BankBOImpl());

		bankUI.bankBo.deserializeBankData();

		do {
			displayOption();
			bankUI.performActionBasedOnCustomerChoice();
		} while (true);

	}

	private void performActionBasedOnCustomerChoice() {
		while (true) {
			try {
				switch (getScannerInstance().nextInt()) {
				case 1:
					addMoreCustomer();
					break;
				case 2:
					addCustomerAccount();
					break;
				case 3:
					sendMoreMoney();
					break;
				case 4:
					bankBo.printBankStatus();
					break;
				case 5:
					System.out.println("Enter the StateCode");
					String stateCode = getScannerInstance().nextLine();

					printCustomerInfoForGivenState(bankBo.getCustomersByState(stateCode));
					break;
				case 6:
					System.out.println("Thanks you!");
					bankBo.serializeBankData();
					System.exit(0);
					break;
				default:
					System.out.println("Not in option!");
					break;
				}
				break; // breaks while loop when it execution point gets here
			} catch (Exception ex) {
				// Validation code
				System.out.println("Please provide valid integer input!");
			}
		}
	}

	private void printCustomerInfoForGivenState(List<Customer> customersByState) {
		System.out.println(customersByState.size() + " Customers Found ");
		for (Customer customer : customersByState) {
			System.out.println(customer);
		}
	}

	private void addCustomerAccount() {
		Customer customer = retrieveCustomerInformation();

		if (customer != null) {
			AccountType accntType = getCustomerChoiceAccountTypeToOpen();

			double openingBalance = getOpeningBalance();

			Account account = new Account(accntType, new Date(), openingBalance);

			customer.addAccount(account);
		} else
			System.out.println("Customer not available!");
	}

	private double getOpeningBalance() {
		System.out.println("Provide Opening Balance in Account: ");
		Scanner accountBalance = getScannerInstance();

		return accountBalance.nextDouble();

	}

	private AccountType getCustomerChoiceAccountTypeToOpen() {
		displayAccountTypeChoice();

		AccountType accountType = null;

		Scanner accountTypeInput = getScannerInstance();
		switch (accountTypeInput.nextInt()) {
		case 1:
			accountType = AccountType.Checking;
			break;
		case 2:
			accountType = AccountType.Saving;
			break;
		case 3:
			accountType = AccountType.Business_Checking;
			break;
		}
		return accountType;
	}

	private void displayAccountTypeChoice() {
		System.out.println("Choose AccountType: [By default is Checkign account]: ");
		System.out.println("1. Checking 2. Saving 3. Business Checking");

	}

	private Customer retrieveCustomerInformation() {
		Scanner accountInput = getScannerInstance();
		System.out.println("Enter Customer's SSN to retrieve His Account Information: ");
		String ssn = accountInput.nextLine();
		
		return bankBo.getCustomerInfo(ssn);
	}

	private void sendMoreMoney() {
		System.out.println("Send Money from the account");
		Account customerChoiceAccount;
		Customer customer = retrieveCustomerInformation();
		if (customer != null) {
			customerChoiceAccount = getAccountChoice(customer);
			Transaction newTransaction = doTransaction();
			System.out.println("New transaction: " + newTransaction);
			customerChoiceAccount.addTransaction(newTransaction);
			System.out.println("New transaction one more time: " + newTransaction);
			deductBalance(customerChoiceAccount, newTransaction);
			System.out.println(bankBo.getCustomerInfo(customer.getSsn()));
			System.out.println(customerChoiceAccount.getTransactions());
			bankBo.sendMoney(customer, customerChoiceAccount, newTransaction);
		} else
			System.err.println("Customer do not exist!!");
	}

	private void deductBalance(Account customerChoiceAccount, Transaction accountTransaction) {
		double fee = accountTransaction.getFee();
		double amount = accountTransaction.getAmount();
		double accountBalance = customerChoiceAccount.getAccountBalance();
		customerChoiceAccount.setAccountBalance(accountBalance - amount- fee);
	}

	private Transaction doTransaction() {
		Scanner sc = getScannerInstance();
		System.out.println("Receiver Firstname");
		String receiverName = sc.nextLine();
		System.out.println("Receiver Lastname");
		String lastName = sc.nextLine();
		System.out.println("Amount to transfer");
		double amount = sc.nextDouble();
		double fee = amount*.1;
		System.out.println("Fee : " + fee);
		return new Transaction(receiverName, lastName, amount, fee, new Date());
	}

	private Account getAccountChoice(Customer customer) {
		Account account = null;

		if (doesCustomerHasAccount(customer)) {
			displayAccountOptions(customer);

			Scanner scan = getScannerInstance();
			int accountChoice = scan.nextInt();

			account = customer.getAccounts().get(accountChoice - 1);
		}
		return account;
	}

	private boolean doesCustomerHasAccount(Customer customer) {
		if (customer.getAccounts().size() < 1) {
			System.out.println("Customer does not have accounts");
			return false;
		}

		return true;
	}

	private void displayAccountOptions(Customer customer) {
		System.out.println("Please choose account to send money:");

		int counter = 1;
		for (Account account : customer.getAccounts()) {
			System.out.println(counter + "." + account.getAccountType() + "(" + account.getAccountNumber() + ")");
			counter++;

		}

	}


	private void CreateTransaction(Customer customer, Account accountType, Double amountToSend) {
		Scanner inputTransaction = new Scanner(System.in);
		List<Account> customerAccounts = customer.getAccounts();

		for (Account cusAcc : customerAccounts) {
			Double balance = cusAcc.getAccountBalance();
			System.out.println(balance);
			System.out.println("Enter the Amount to send");
			Double amountToTransfer = inputTransaction.nextDouble();

			System.out.println("Final balance of the account:" + (balance - amountToTransfer));

		}

	}

	private void addMoreCustomer() {
		Customer customer = retrieveCustomerObjectFromCustomerInput();

		bankBo.addCustomer(customer);
		bankBo.printBankStatus();
	}

	private Customer retrieveCustomerObjectFromCustomerInput() {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter Customer  First Name");
		String firstName = input.nextLine();

		System.out.println("Enter Customer Last Name");
		String lastName = input.nextLine();

		System.out.println("Enter Customer Address in Format (streetname,zipcode,city,statecode)");
		String streetName = input.nextLine();
		String zipCode = input.nextLine();
		String city = input.nextLine();
		String stateCode = input.nextLine();

		System.out.println("Enter your Social Security");
		String ssn = input.nextLine();

		return new Customer(firstName, lastName, new Address(streetName, zipCode, city, stateCode), ssn);

	}

	private Scanner getScannerInstance() {
		return new Scanner(System.in);
	}

	private static void displayOption() {
		System.out.println("choose from the Menu below");
		System.out.println("1. Add Customer");
		System.out.println("2. Add Account");
		System.out.println("3. Send Money");
		System.out.println("4. Print Bank Status");
		System.out.println("5. Print all customer by state");
		System.out.println("6. Exit");

	}

}

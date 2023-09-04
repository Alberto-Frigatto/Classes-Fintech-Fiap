package com.bankAccounts;

import com.bank.Bank;
import com.bankAccounts.exceptions.BankAccountExceptions;
import com.bankAccounts.exceptions.BankAccountExceptions.BankAccountException;
import com.client.Client;

public abstract class BankAccount
{
    private Client owner;
    private Bank bank;
    private String number;
    private double balance = 0;
    private String branch;
    private KindAccount kindAccount;
    private boolean isActive = true;

    public BankAccount(Client owner, Bank bank, String number, String branch, KindAccount kindAccount) throws BankAccountException
    {
        this.owner = owner;
        this.bank = bank;
        this.number = number.strip();
        this.branch = branch.strip();
        this.kindAccount = kindAccount;

        this.validateNumber();
        this.validateBranch();
    }

    static public enum KindAccount
    {
        POUPANCA,
        CORRENTE,
        SALARIO
    }

    private void validateNumber() throws BankAccountExceptions.InvalidNumberException
    {
        if (!this.numberIsValid())
            throw new BankAccountExceptions.InvalidNumberException();
    }

    private boolean numberIsValid()
    {
        int maxLength = 10;

        if (this.number.isEmpty() || this.number.length() > maxLength)
            return false;

        for (char c : this.number.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }
    
    private void validateBranch() throws BankAccountExceptions.InvalidBranchException
    {
        if (!this.branchIsValid())
        throw new BankAccountExceptions.InvalidBranchException();
    }

    private boolean branchIsValid()
    {
        int maxLength = 6;

        if (this.branch.isEmpty() || this.branch.length() > maxLength)
            return false;

        for (char c : this.branch.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    public void deposit(int value) throws BankAccountExceptions.InvalidTransactionException
	{
        if (!transactionValueIsValid(value))
            throw new BankAccountExceptions.InvalidTransactionException();

		this.balance += value;
	}

    private boolean transactionValueIsValid(double value)
    {
        return value > 0;
    }
    
    public void draft(int value) throws BankAccountExceptions.InvalidTransactionException
	{
        if (!this.transactionIsPossible(value) || !this.transactionValueIsValid(value))
            throw new BankAccountExceptions.InvalidTransactionException();
        
		this.balance -= value;
	}
    
    public void transfer(int value, BankAccount account) throws BankAccountExceptions.InvalidTransactionException
	{
        if (!this.transactionIsPossible(value) || !this.transactionValueIsValid(value))
            throw new BankAccountExceptions.InvalidTransactionException();
        
		this.draft(value);
		account.deposit(value);
	}

    abstract protected boolean transactionIsPossible(double value);

    public void cancelAccount()
    {
        if (this.isActive)
            this.isActive = false;
    }

    public String getBranch()
	{
		return this.branch;
	}
	
	public String getNumber()
	{
		return this.number;
	}
	
	public double getBalance()
	{
		return this.balance;
	}
	
	public void setBalance(double balance)
	{
		this.balance = balance;
    }
	
	public Client getOwner()
	{
		return this.owner;
	}
	
	public KindAccount getkindAccount()
	{
		return this.kindAccount;
	}
	
	public Bank getBank()
	{
		return this.bank;
	}
    
    public boolean getIsActive()
    {
        return this.isActive;
    }
}

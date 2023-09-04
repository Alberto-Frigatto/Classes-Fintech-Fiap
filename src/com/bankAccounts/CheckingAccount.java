package com.bankAccounts;

import com.bank.Bank;
import com.bankAccounts.exceptions.BankAccountExceptions.BankAccountException;
import com.bankAccounts.exceptions.CheckingAccountExceptions;
import com.client.Client;

public class CheckingAccount extends BankAccount
{
    private float limit;

    public CheckingAccount(Client owner, Bank bank, String number, String branch, float limit) throws BankAccountException
    {
        super(owner, bank, number, branch, BankAccount.KindAccount.CORRENTE);
        this.limit = limit;

        this.validateLimit();
    }

    private void validateLimit() throws CheckingAccountExceptions.InvalidLimitException
    {
        if (!this.limitIsValid())
            throw new CheckingAccountExceptions.InvalidLimitException();
    }

    private boolean limitIsValid()
    {
        return this.limit > 0;
    }

    @Override
    protected boolean transactionIsPossible(double value)
    {
        return this.getBalance() + this.limit >= value;
    }

    public void getData()
	{
		System.out.println("\nTITULAR: " + this.getOwner().getName());
		System.out.println("TIPO: " + this.getkindAccount());
		System.out.println("ESTÁ: " + (this.getIsActive() ? "ATIVA" : "CANCELADA"));
		System.out.println("BANCO: " + this.getBank().getName());
		System.out.println("AGÊNCIA: " + this.getBranch());
		System.out.println("NÚMERO: " + this.getNumber());
		System.out.println("SALDO: R$" + this.getBalance());
		System.out.println("LIMITE: R$" + this.limit);
		System.out.println("PESSOA: " + this.getOwner().getkindPerson() + "\n");
	}

    public float getLimit()
    {
        return this.limit;
    }
}

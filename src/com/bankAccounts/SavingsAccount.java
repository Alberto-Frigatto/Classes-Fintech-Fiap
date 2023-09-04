package com.bankAccounts;

import java.text.DecimalFormat;

import com.bank.Bank;
import com.bankAccounts.exceptions.BankAccountExceptions.BankAccountException;
import com.client.Client;

public class SavingsAccount extends BankAccount
{
	private float incomeRate = .0551f;

    public SavingsAccount(Client owner, Bank bank, String number, String branch) throws BankAccountException
    {
        super(owner, bank, number, branch, BankAccount.KindAccount.POUPANCA);
    }

    @Override
    protected boolean transactionIsPossible(double value)
	{
        return this.getBalance() >= value;
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
		System.out.println("PESSOA: " + this.getOwner().getkindPerson() + "\n");
	}

	public void applyMonthlyIncome()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		double income = this.incomeRate * this.getBalance();
        double newBalance = Double.parseDouble(df.format(this.getBalance() + income).replace(',', '.'));

		this.setBalance(newBalance);
	}

    public float getIncomeRate()
	{
      return this.incomeRate;
    }
}

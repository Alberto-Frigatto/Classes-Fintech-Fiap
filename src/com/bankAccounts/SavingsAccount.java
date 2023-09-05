package com.bankAccounts;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.bank.Bank;
import com.bankAccounts.exceptions.BankAccountExceptions;
import com.bankAccounts.exceptions.BankAccountExceptions.BankAccountException;
import com.client.Client;
import com.client.PhysicalClient;
import com.jointAccount.JointAccount;

public class SavingsAccount extends BankAccount implements JointAccount
{
    private float incomeRate = .0551f;
    private ArrayList<Client> owners = new ArrayList<>();

    public SavingsAccount(Client owner, Bank bank, String number, String branch) throws BankAccountException
    {
        super(owner, bank, number, branch, BankAccount.KindAccount.POUPANCA);

        this.owners.add(owner);
    }

    @Override
    protected boolean transactionIsPossible(double value)
    {
        return this.getBalance() >= value;
    }

    public void getData()
    {
        System.out.println("\nTITULAR: " + this.getOwner().getName());
        System.out.println("2º TITULAR: " + (this.owners.size() == 2 ? this.owners.get(1).getName() : "Não há"));
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

    @Override
    public void includeOwner(PhysicalClient owner) throws BankAccountExceptions.InvalidOwnerAddictionException
    {
        if (!this.addicitionIsPossible(owner))
            throw new BankAccountExceptions.InvalidOwnerAddictionException();

        this.owners.add(owner);
    }

    private boolean addicitionIsPossible(PhysicalClient owner)
    {
        return this.owners.size() < 2 && owner != this.getOwner();
    }

    
    public ArrayList<Client> getOwners()
    {
      return this.owners;
    }
}

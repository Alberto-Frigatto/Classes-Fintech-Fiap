package com.bankAccounts;

import java.util.ArrayList;

import com.bank.Bank;
import com.bankAccounts.exceptions.BankAccountExceptions;
import com.bankAccounts.exceptions.BankAccountExceptions.BankAccountException;
import com.bankAccounts.exceptions.CheckingAccountExceptions;
import com.client.Client;
import com.client.PhysicalClient;
import com.jointAccount.JointAccount;

public class CheckingAccount extends BankAccount implements JointAccount
{
    private float limit;
    private ArrayList<Client> owners = new ArrayList<>();

    public CheckingAccount(Client owner, Bank bank, String number, String branch, float limit) throws BankAccountException
    {
        super(owner, bank, number, branch, BankAccount.KindAccount.CORRENTE);
        this.limit = limit;
        this.owners.add(owner);

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

    @Override
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
        System.out.println("LIMITE: R$" + this.limit);
        System.out.println("PESSOA: " + this.getOwner().getkindPerson() + "\n");
    }

    public float getLimit()
    {
        return this.limit;
    }

    public ArrayList<Client> getOwners()
    {
      return this.owners;
    }
}

package com.bankAccounts;

import java.util.ArrayList;

import com.bank.Bank;
import com.bankAccounts.exceptions.SalaryAccountExceptions;
import com.bankAccounts.exceptions.BankAccountExceptions.BankAccountException;
import com.client.Client;
import com.company.Company;
import com.debit.AutomaticDebit;
import com.debit.Debit;

public class SalaryAccount extends BankAccount implements AutomaticDebit
{
    private String pis;
    private String workCard;
    private Company company;
    private ArrayList<Debit> automaticDebits = new ArrayList<>();

    public SalaryAccount(
        Client owner,
        Bank bank,
        String number,
        String branch,
        String pis,
        String workCard,
        Company company
    ) throws BankAccountException
    {
        super(owner, bank, number, branch, BankAccount.KindAccount.SALARIO);

        this.pis = pis.strip();
        this.workCard = workCard.strip();
        this.company = company;

        this.validateKindClient();
        this.validatePis();
        this.validateWorkCard();
    }

    private void validateKindClient() throws SalaryAccountExceptions.InvalidKindClientException
    {
        if (!this.kindClientIsValid())
            throw new SalaryAccountExceptions.InvalidKindClientException();
    }

    private boolean kindClientIsValid()
    {
        return this.getOwner().getkindPerson() == Client.KindPerson.PF;
    }

    private void validatePis() throws SalaryAccountExceptions.InvalidPisException
    {
        if (!this.pisIsValid())
            throw new SalaryAccountExceptions.InvalidPisException();
    }

    private boolean pisIsValid()
    {
        int maxLength = 11;

        if (this.pis.isEmpty() || this.pis.length() != maxLength)
            return false;

            for (char c : this.pis.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }
    
    private void validateWorkCard() throws SalaryAccountExceptions.InvalidWorkCardException
    {
        if (!this.workCardIsValid())
            throw new SalaryAccountExceptions.InvalidWorkCardException();
    }

    private boolean workCardIsValid()
    {
        int maxLength = 11;

        if (this.workCard.isEmpty() || this.workCard.length() != maxLength)
            return false;

        for (char c : this.workCard.toCharArray())
            if (!Character.isDigit(c))
            return false;
            
            return true;
    }

    @Override
    public void includeAutomaticDebit(Debit debit)
    {
        this.automaticDebits.add(debit);
    }

    @Override
    public void payAutomaticDebits()
    {
        double totalValue = 0;

        for (Debit automaticDebit : this.automaticDebits)
            totalValue += automaticDebit.getValue();

        this.setBalance(this.getBalance() - totalValue);
    }

    @Override
    protected boolean transactionIsPossible(double value)
    {
        return this.getBalance() >= value;
    }

    @Override
    public void getData()
    {
        System.out.println("\nTITULAR: " + this.getOwner().getName());
        System.out.println("TIPO: " + this.getkindAccount());
        System.out.println("ESTÁ: " + (this.getIsActive() ? "ATIVA" : "CANCELADA"));
        System.out.println("BANCO: " + this.getBank().getName());
        System.out.println("AGÊNCIA: " + this.getBranch());
        System.out.println("NÚMERO: " + this.getNumber());
        System.out.println("SALDO: R$" + this.getBalance());
        System.out.println("EMPRESA: " + this.company.getName());
        System.out.println("PESSOA: " + this.getOwner().getkindPerson() + "\n");
    }

    public String getPis()
    {
        return this.pis;
    }
    
    public String getWorkCard()
    {
        return this.workCard;
    }

    public Company getCompany()
    {
        return this.company;
    }

    public ArrayList<Debit> getAutomaticDebits()
    {
        return this.automaticDebits;
    }
}

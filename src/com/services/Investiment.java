package com.services;

import java.text.DecimalFormat;

import com.bankAccounts.BankAccount;
import com.bankAccounts.exceptions.BankAccountExceptions.InvalidTransactionException;
import com.services.exceptions.InvestimentExceptions;
import com.services.exceptions.ServiceExceptions.ServiceException;

public class Investiment extends Service
{
    private KindInvestment kindInvestiment;
    private int waitingPeriod;
    private double investedAmount;
    private double interest = 0;

    public Investiment(
        float serviceFee,
        BankAccount bankAccount,
        KindInvestment kindInvestiment,
        int waitingPeriod,
        double investedAmount
    ) throws ServiceException
    {
        super(Service.KindService.INVESTMENT, serviceFee, bankAccount);

        this.kindInvestiment = kindInvestiment;
        this.waitingPeriod = waitingPeriod;
        this.investedAmount = investedAmount;

        this.validateKindAccount();
        this.validateWaitingPeriod();
        this.validateInvestedAmount();
    }

    public static enum KindInvestment
    {
        LCA,
        LCI,
        TESOURO_DIRETO,
        CDB
    }

    private void validateKindAccount() throws InvestimentExceptions.InvalidkindAccountException
    {
        if (!this.kindAccountIsValid())
            throw new InvestimentExceptions.InvalidkindAccountException();
    }

    private boolean kindAccountIsValid()
    {
        return this.getBankAccount().getkindAccount() == BankAccount.KindAccount.CORRENTE;
    }

    private void validateWaitingPeriod() throws InvestimentExceptions.InvalidWaitingPeriodException
    {
        if (!this.waitingPeriodIsValid())
            throw new InvestimentExceptions.InvalidWaitingPeriodException();
    }

    private boolean waitingPeriodIsValid()
    {
        return this.waitingPeriod >= 0 && this.waitingPeriod <= 60;
    }

    private void validateInvestedAmount() throws InvestimentExceptions.InvalidInvestedAmountException
    {
        if (!this.investedAmountIsValid())
            throw new InvestimentExceptions.InvalidInvestedAmountException();
    }

    private boolean investedAmountIsValid()
    {
        return this.investedAmount > 0;
    }

    public void applyInterest()
    {
        DecimalFormat df = new DecimalFormat("#.##");
        double interest = this.calculateInterest();
        double formattedInterest = Double.parseDouble(df.format(interest).replace(',', '.'));

        this.interest += formattedInterest;

        try
        {
            this.getBankAccount().deposit(formattedInterest);
        }
        catch (InvalidTransactionException e)
        {
            System.err.println(e.getMessage());
        }
    }

    private double calculateInterest()
    {
        return this.investedAmount * this.getServiceFee();
    }

    public KindInvestment getKindInvestiment()
    {
      return this.kindInvestiment;
    }

    public int getWaitingPeriod()
    {
      return this.waitingPeriod;
    }

    public double getInvestedAmount()
    {
      return this.investedAmount;
    }

    public double getInterest()
    {
      return this.interest;
    }
}

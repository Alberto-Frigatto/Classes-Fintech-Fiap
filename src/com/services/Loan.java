package com.services;

import com.bankAccounts.BankAccount;
import com.bankAccounts.exceptions.BankAccountExceptions;
import com.services.exceptions.ServiceExceptions.ServiceException;
import com.services.exceptions.LoanExceptions;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Loan extends Service
{
    private double value;
    private int portions;
    private double portionValue;
    private LocalDate initialDate;
    private LocalDate endDate;

    public Loan(
        float serviceFee,
        BankAccount bankAccount,
        double value,
        String endDate
    ) throws ServiceException
    {
        super(Service.KindService.LOAN, serviceFee, bankAccount);

        this.value = value;

        this.validateValue();
        this.setInitialDate();
        this.setEndDateIfValid(endDate);
        this.setPortions();
        this.setPortionValue();
        this.depositValue();
    }

    private void validateValue() throws LoanExceptions.InvalidValueException
    {
        if (!this.valueIsValid())
            throw new LoanExceptions.InvalidValueException();
    }

    private boolean valueIsValid()
    {
        return this.value > 0;
    }

    private void setInitialDate()
    {
        this.initialDate = LocalDate.now();
    }
    
    private void setEndDateIfValid(String endDate) throws LoanExceptions.InvalidEndDateException
    {
        if (!this.endDateIsvalid(endDate))
        throw new LoanExceptions.InvalidEndDateException();
        
        this.endDate = LocalDate.parse(endDate);
    }

    private boolean endDateIsvalid(String endDate)
    {
        return LocalDate.parse(endDate).isAfter(this.initialDate);
    }

    private void setPortions()
    {
        int portions = this.getMonthDifference();

        this.portions = portions;
    }

    private int getMonthDifference()
    {
        int monthDifference = (int) ChronoUnit.MONTHS.between(this.initialDate, this.endDate);

        return monthDifference;
    }

    private void setPortionValue()
    {
        DecimalFormat df = new DecimalFormat("#.##");
        double portionValue = this.calculatePortionValue();
        double formattedPortionValue = Double.parseDouble(df.format(portionValue).replace(',', '.'));

        this.portionValue = formattedPortionValue;
    }

    private double calculatePortionValue()
    {
        double portionValue = this.value / this.portions;

        return portionValue;
    }

    private void depositValue()
    {
        try
        {
            this.getBankAccount().deposit(this.value);
        }
        catch (BankAccountExceptions.InvalidTransactionException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public void payPortion()
    {
        BankAccount account = this.getBankAccount();

        double newBalance = account.getBalance() - this.portionValue;
        account.setBalance(newBalance);
    }

    public double getValue()
    {
        return this.value;
    }

    public long getPortions()
    {
        return this.portions;
    }

    public LocalDate getInitialDate()
    {
        return this.initialDate;
    }

    public LocalDate getEndDate()
    {
        return this.endDate;
    }

    public double getPortionValue() {
      return this.portionValue;
    }
}

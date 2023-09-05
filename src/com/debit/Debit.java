package com.debit;

import com.bank.Bank;
import com.debit.exceptions.DebitExceptions;
import com.debit.exceptions.DebitExceptions.DebitException;

public class Debit
{
    private Bank bank;
    private double value;
    private String payee;
    private int day;
    private int period;

    public Debit (Bank bank, double value, String payee, int day, int period) throws DebitException
    {
        this.bank = bank;
        this.value = value;
        this.payee = payee.strip();
        this.day = day;
        this.period = period;

        this.validateValue();
        this.validatePayee();
        this.validateDay();
        this.validatePeriod();
    }

    private void validateValue() throws DebitExceptions.InvalidValueException
    {
        if (!this.valueIsValid())
            throw new DebitExceptions.InvalidValueException();
    }

    private boolean valueIsValid()
    {
        return this.value > 0;
    }

    private void validatePayee() throws DebitExceptions.InvalidPayeeException
    {
        if (!this.payeeIsValid())
            throw new DebitExceptions.InvalidPayeeException();
    }

    private boolean payeeIsValid()
    {
        return this.payee instanceof String && !this.payee.isEmpty() && this.payee.length() <= 50;
    }

    private void validateDay() throws DebitExceptions.InvalidDayException
    {
        if (!this.dayIsValid())
            throw new DebitExceptions.InvalidDayException();
    }

    private boolean dayIsValid()
    {
        return this.day > 0 && this.day <= 31;
    }

    private void validatePeriod() throws DebitExceptions.InvalidPeriodException
    {
        if (!this.periodIsValid())
            throw new DebitExceptions.InvalidPeriodException();
    }

    private boolean periodIsValid()
    {
        return this.period > 0 && this.period <= 365;
    }

    public double getValue()
    {
        return this.value;
    }

    public String getPayee()
    {
        return this.payee;
    }

    public int getDay()
    {
        return this.day;
    }

    public int getPeriod()
    {
        return this.period;
    }

    public Bank getBank()
    {
      return this.bank;
    }
}

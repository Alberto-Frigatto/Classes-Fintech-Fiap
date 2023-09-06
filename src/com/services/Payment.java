package com.services;

import com.bank.Bank;
import com.bankAccounts.BankAccount;
import com.bankAccounts.exceptions.BankAccountExceptions.InvalidTransactionException;
import com.services.exceptions.PaymentExceptions;
import com.services.exceptions.ServiceExceptions.ServiceException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Payment extends Service
{
    private double value;
    private LocalDate dueDate;
    private Bank bank;
    private String barCode;
    private LocalDate paymentDate = LocalDate.now();
    private boolean isPay = true;
    private float fees;
    private String name;
    private String payee;

    public Payment (
        float serviceFee,
        BankAccount bankAccount,
        double value,
        String dueDate,
        Bank bank,
        String barCode,
        float fees,
        String name,
        String payee
    ) throws ServiceException
    { 
        super(Service.KindService.PAYMENT, serviceFee, bankAccount);

        this.value = value;
        this.bank = bank;
        this.barCode = barCode.strip();
        this.fees = fees;
        this.name = name.strip();
        this.payee = payee.strip();


        this.validateValue();
        this.setDueDateIfValid(dueDate);
        this.validateBarCode();
        this.validateFees();
        this.validateName();
        this.validatePayee();
        this.removeValueFromAccount();
    }

    private void validateValue() throws PaymentExceptions.InvalidValueException
    {
        if (!this.valueIsValid())
            throw new PaymentExceptions.InvalidValueException();
    }

    private boolean valueIsValid()
    {
        return this.value > 0;
    }

    private void setDueDateIfValid(String dueDate) throws PaymentExceptions.InvalidDueDateException
    {
        if (!this.dueDateIsValid(dueDate))
            throw new PaymentExceptions.InvalidDueDateException();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        this.dueDate = LocalDate.parse(dueDate, formatter);
    }

    private boolean dueDateIsValid(String dueDate)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try
        {
            LocalDate.parse(dueDate, formatter);
        }
        catch (java.time.format.DateTimeParseException e)
        {
            return false;
        }

        return true;
    }

    private void validateBarCode() throws PaymentExceptions.InvalidBarCodeException
    {
        if (!this.barCodeIsValid())
            throw new PaymentExceptions.InvalidBarCodeException();
    }

    private boolean barCodeIsValid()
    {
        int minLength = 44;
        int maxLength = 48;

        if (this.barCode.isEmpty() || this.barCode.length() < minLength || this.barCode.length() > maxLength)
            return false;

        for (char c : this.barCode.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private void validateFees() throws PaymentExceptions.InvalidFeesException
    {
        if (!this.feesIsValid())
            throw new PaymentExceptions.InvalidFeesException();
    }

    private boolean feesIsValid()
    {
        return this.fees > 0 && this.fees <= 1;
    }

    private void validateName() throws PaymentExceptions.InvalidNameException
    {
        if (!this.nameIsValid())
            throw new PaymentExceptions.InvalidNameException();
    }

    private boolean nameIsValid()
    {
        return this.name instanceof String && !this.name.isEmpty() && this.name.length() <= 50;
    }

    private void validatePayee() throws PaymentExceptions.InvalidPayeeException
    {
        if (!this.payeeIsValid())
            throw new PaymentExceptions.InvalidPayeeException();
    }

    private boolean payeeIsValid()
    {
        return this.payee instanceof String && !this.payee.isEmpty() && this.payee.length() <= 50;
    }

    private void removeValueFromAccount()
    {
        try
        {
            this.getBankAccount().draft(this.value);
        }
        catch (InvalidTransactionException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public double getValue()
    {
      return this.value;
    }

    public LocalDate getDueDate()
    {
      return this.dueDate;
    }

    public Bank getBank()
    {
      return this.bank;
    }

    public String getBarCode()
    {
      return this.barCode;
    }

    public LocalDate getPaymentDate()
    {
      return this.paymentDate;
    }

    public boolean getIsPay()
    {
      return this.isPay;
    }

    public float getFees()
    {
      return this.fees;
    }

    public String getName()
    {
      return this.name;
    }

    public String getPayee()
    {
      return this.payee;
    }
}

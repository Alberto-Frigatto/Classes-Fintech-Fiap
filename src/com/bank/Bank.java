package com.bank;

import com.bank.exceptions.BankExceptions;
import com.bank.exceptions.BankExceptions.BankException;
import com.legalPerson.LegalPerson;

public class Bank
{
    private String name;
    private String cnpj;
    private String number;

    public Bank(String name, String cnpj, String number) throws BankException
    {
        this.name = name.strip();
        this.cnpj = cnpj.strip();
        this.number = number.strip();

        this.validateName();
        this.validateNumber();
        this.validateCnpj();
    }

    private void validateName() throws BankExceptions.InvalidNameException
    {
        if (!this.nameIsValid())
            throw new BankExceptions.InvalidNameException();
    }

    private boolean nameIsValid()
    {
        return this.name instanceof String && !this.name.isEmpty() && this.name.length() <= 50;
    }

    private void validateNumber() throws BankExceptions.InvalidNumberException
    {
        if (!this.numberIsValid())
            throw new BankExceptions.InvalidNumberException();
    }

    private boolean numberIsValid()
    {
        int maxLength = 6;

        if (this.number.isEmpty() || this.number.length() > maxLength)
            return false;

        for (char c : this.number.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private void validateCnpj() throws BankExceptions.InvalidCnpjException
    {
        if (!LegalPerson.cnpjIsValid(this.cnpj))
            throw new BankExceptions.InvalidCnpjException();
    }

    public String getName()
    {
        return this.name;
    }

    public String getCnpj()
    {
        return this.cnpj;
    }

    public String getNumber()
    {
        return this.number;
    }
}

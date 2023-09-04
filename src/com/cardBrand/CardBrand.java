package com.cardBrand;

import com.cardBrand.exceptions.CardBrandExceptions;
import com.cardBrand.exceptions.CardBrandExceptions.CardBrandException;
import com.legalPerson.LegalPerson;

public class CardBrand
{
    private String name;
    private String cnpj;
    private int numberLength;
    private int cvvLength;

    public CardBrand(String name, String cnpj, int numberLength, int cvvLength) throws CardBrandException
    {
        this.name = name.strip();
        this.cnpj = cnpj.strip();
        this.numberLength = numberLength;
        this.cvvLength = cvvLength;

        this.validateName();
        this.validateCnpj();
        this.validateNumberLength();
        this.validateCvvLength();
    }

    private void validateName() throws CardBrandExceptions.InvalidNameException
    {
        if (!this.nameIsValid())
            throw new CardBrandExceptions.InvalidNameException();
    }

    private boolean nameIsValid()
    {
        return this.name instanceof String && !this.name.isEmpty() && this.name.length() <= 50 && this.name.matches("^[^0-9]*$");
    }

    private void validateCnpj() throws CardBrandExceptions.InvalidCnpjException
    {
        if (!LegalPerson.cnpjIsValid(this.cnpj))
            throw new CardBrandExceptions.InvalidCnpjException();
    }

    private void validateNumberLength() throws CardBrandExceptions.InvalidNumberLengthException
    {
        if (!this.numberLengthIsValid())
            throw new CardBrandExceptions.InvalidNumberLengthException();
    }

    private boolean numberLengthIsValid()
    {
        int minLength = 14;
        int maxLength = 16;

        return this.numberLength >= minLength || this.numberLength <= maxLength;
    }

    private void validateCvvLength() throws CardBrandExceptions.InvalidNumberLengthException
    {
        if (!this.cvvLengthIsValid())
            throw new CardBrandExceptions.InvalidNumberLengthException();
    }

    private boolean cvvLengthIsValid()
    {
        int minLength = 3;
        int maxLength = 4;

        return this.cvvLength == minLength || this.cvvLength == maxLength;
    }

    public String getName()
    {
        return this.name;
    }

    public String getCnpj()
    {
        return this.cnpj;
    }

    public int getNumberLength()
    {
        return this.numberLength;
    }

    public int getCvvLength()
    {
        return this.cvvLength;
    }
}

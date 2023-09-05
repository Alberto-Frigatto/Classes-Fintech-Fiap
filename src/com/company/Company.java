package com.company;

import com.company.exceptions.CompanyExceptions.CompanyException;
import com.company.exceptions.CompanyExceptions;
import com.legalPerson.LegalPerson;

public class Company
{
    private String name;
    private String cnpj;

    public Company(String name, String cnpj)  throws CompanyException
    {
        this.name = name;
        this.cnpj = cnpj;

        this.validateName();
        this.validateCnpj();
    }

    private void validateName() throws CompanyExceptions.InvalidNameException
    {
        if (!this.nameIsValid())
            throw new CompanyExceptions.InvalidNameException();
    }

    private boolean nameIsValid()
    {
        return this.name instanceof String && !this.name.isEmpty() && this.name.length() <= 50;
    }

    private void validateCnpj() throws CompanyExceptions.InvalidCnpjException
    {
        if (!LegalPerson.cnpjIsValid(this.cnpj))
            throw new CompanyExceptions.InvalidCnpjException();
    }

    public String getName()
    {
      return this.name;
    }

    public String getCnpj()
    {
      return this.cnpj;
    }
}

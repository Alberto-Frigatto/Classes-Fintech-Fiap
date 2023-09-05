package com.company.exceptions;

public class CompanyExceptions
{
    public static abstract class CompanyException extends Exception
    {
        public CompanyException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidNameException extends CompanyException
    {
        static final String MESSAGE = "Nome inválido";

        public InvalidNameException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidCnpjException extends CompanyException
    {
        static final String MESSAGE = "CNPJ inválido";

        public InvalidCnpjException()
        {
            super(MESSAGE);
        }
    }
}

package com.bankAccounts.exceptions;

import com.bankAccounts.exceptions.BankAccountExceptions.BankAccountException;

public class SalaryAccountExceptions
{
    public static abstract class SalaryAccountException extends BankAccountException
    {
        public SalaryAccountException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidPisException extends SalaryAccountException
    {
        static final String MESSAGE = "Número de PIS inválido";

        public InvalidPisException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidKindClientException extends SalaryAccountException
    {
        static final String MESSAGE = "Tipo de cliente inválido";

        public InvalidKindClientException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidWorkCardException extends SalaryAccountException
    {
        static final String MESSAGE = "Número de carteira de trabalho inválido";

        public InvalidWorkCardException()
        {
            super(MESSAGE);
        }
    }
}

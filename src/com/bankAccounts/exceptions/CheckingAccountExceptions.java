package com.bankAccounts.exceptions;

import com.bankAccounts.exceptions.BankAccountExceptions.BankAccountException;

public class CheckingAccountExceptions
{
    public abstract static class CheckingAccountException extends BankAccountException
    {
        public CheckingAccountException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidLimitException extends CheckingAccountException
    {
        static final String MESSAGE = "Limite inválido";

        public InvalidLimitException()
        {
            super(MESSAGE);
        }
    }
}

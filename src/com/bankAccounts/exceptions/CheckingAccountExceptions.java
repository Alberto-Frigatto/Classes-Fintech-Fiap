package com.bankAccounts.exceptions;

import com.bankAccounts.exceptions.BankAccountExceptions.BankAccountException;

public class CheckingAccountExceptions
{
    public abstract static class CheckingAccountException extends BankAccountException
    {
        public CheckingAccountException(String message)
        {
            super(message);
        }
    }

    public static class InvalidLimitException extends CheckingAccountException
    {
        static String MESSAGE = "\nLimite inválido\n";

        public InvalidLimitException()
        {
            super(MESSAGE);
        }
    }
}

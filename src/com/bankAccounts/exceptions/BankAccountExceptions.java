package com.bankAccounts.exceptions;

public class BankAccountExceptions
{
    public abstract static class BankAccountException extends Exception
    {
        public BankAccountException(String message)
        {
            super(message);
        }
    }

    public static class InvalidNumberException extends BankAccountException
    {
        static String MESSAGE = "\nNúmero da conta inválido\n";

        public InvalidNumberException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidBranchException extends BankAccountException
    {
        static String MESSAGE = "\nAgência inválida\n";

        public InvalidBranchException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidTransactionException extends BankAccountException
    {
        static String MESSAGE = "\nTransação não permitida\n";

        public InvalidTransactionException()
        {
            super(MESSAGE);
        }
    }
}

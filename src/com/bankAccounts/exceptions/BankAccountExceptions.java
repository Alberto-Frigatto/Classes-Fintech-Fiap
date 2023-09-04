package com.bankAccounts.exceptions;

public class BankAccountExceptions
{
    public abstract static class BankAccountException extends Exception
    {
        public BankAccountException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidNumberException extends BankAccountException
    {
        static final String MESSAGE = "Número da conta inválido";

        public InvalidNumberException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidBranchException extends BankAccountException
    {
        static final String MESSAGE = "Agência inválida";

        public InvalidBranchException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidTransactionException extends BankAccountException
    {
        static final String MESSAGE = "Transação não permitida";

        public InvalidTransactionException()
        {
            super(MESSAGE);
        }
    }
}

package com.bank.exceptions;

public class BankExceptions
{
    static public abstract class BankException extends Exception
    {
        public BankException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    static public class InvalidCnpjException extends BankException
    {
        static final String MESSAGE = "CNPJ do banco inválido";

        public InvalidCnpjException()
        {
            super(MESSAGE);
        }
    }

    static public class InvalidNameException extends BankException
    {
        static final String MESSAGE = "Nome do banco inválido";

        public InvalidNameException()
        {
            super(MESSAGE);
        }
    }

    static public class InvalidNumberException extends BankException
    {
        static final String MESSAGE = "Número do banco inválido";

        public InvalidNumberException()
        {
            super(MESSAGE);
        }
    }
}

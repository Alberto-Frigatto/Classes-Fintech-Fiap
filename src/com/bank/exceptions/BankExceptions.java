package com.bank.exceptions;

public class BankExceptions
{
    static public abstract class BankException extends Exception
    {
        public BankException(String message)
        {
            super(message);
        }
    }

    static public class InvalidCnpjException extends BankException
    {
        static String MESSAGE = "\nCNPJ do banco inválido\n";

        public InvalidCnpjException()
        {
            super(MESSAGE);
        }
    }

    static public class InvalidNameException extends BankException
    {
        static String MESSAGE = "\nNome do banco inválido\n";

        public InvalidNameException()
        {
            super(MESSAGE);
        }
    }

    static public class InvalidNumberException extends BankException
    {
        static String MESSAGE = "\nNúmero do banco inválido\n";

        public InvalidNumberException()
        {
            super(MESSAGE);
        }
    }
}

package com.cardBrand.exceptions;

public class CardBrandExceptions
{
    public static abstract class CardBrandException extends Exception
    {
        public CardBrandException(String message)
        {
            super(message);
        }
    }

    public static class InvalidNameException extends CardBrandException
    {
        static String MESSAGE = "\nNome inválido\n";

        public InvalidNameException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidCnpjException extends CardBrandException
    {
        static String MESSAGE = "\nCnpj inválido\n";

        public InvalidCnpjException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidNumberLengthException extends CardBrandException
    {
        static String MESSAGE = "\nTamanho de número de cartão inválido\n";

        public InvalidNumberLengthException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidCvvLengthException extends CardBrandException
    {
        static String MESSAGE = "\nTamanho de cvv de cartão inválido\n";

        public InvalidCvvLengthException()
        {
            super(MESSAGE);
        }
    }
}
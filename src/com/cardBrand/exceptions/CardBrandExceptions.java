package com.cardBrand.exceptions;

public class CardBrandExceptions
{
    public static abstract class CardBrandException extends Exception
    {
        public CardBrandException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidNameException extends CardBrandException
    {
        static final String MESSAGE = "Nome inválido";

        public InvalidNameException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidCnpjException extends CardBrandException
    {
        static final String MESSAGE = "Cnpj inválido";

        public InvalidCnpjException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidNumberLengthException extends CardBrandException
    {
        static final String MESSAGE = "Tamanho de número de cartão inválido";

        public InvalidNumberLengthException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidCvvLengthException extends CardBrandException
    {
        static final String MESSAGE = "Tamanho de cvv de cartão inválido";

        public InvalidCvvLengthException()
        {
            super(MESSAGE);
        }
    }
}
package com.services.exceptions;

import com.services.exceptions.ServiceExceptions.ServiceException;

public class CardExceptions
{
    public static abstract class CardException extends ServiceException
    {
        public CardException(String message)
        {
            super(message);
        }
    }

    public static class InvalidNumberException extends CardException
    {
        static String MESSAGE = "\nNúmero inválido\n";

        public InvalidNumberException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidPasswordException extends CardException
    {
        static String MESSAGE = "\nSenha inválida\n";

        public InvalidPasswordException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidValidityException extends CardException
    {
        static String MESSAGE = "\nValidade inválida\n";

        public InvalidValidityException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidCvvException extends CardException
    {
        static String MESSAGE = "\nCvv inválido\n";

        public InvalidCvvException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidAnnualFeeException extends CardException
    {
        static String MESSAGE = "\nAnuidade inválida\n";

        public InvalidAnnualFeeException()
        {
            super(MESSAGE);
        }
    }
}

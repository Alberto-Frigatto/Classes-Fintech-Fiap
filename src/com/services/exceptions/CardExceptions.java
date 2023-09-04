package com.services.exceptions;

import com.services.exceptions.ServiceExceptions.ServiceException;

public class CardExceptions
{
    public static abstract class CardException extends ServiceException
    {
        public CardException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidNumberException extends CardException
    {
        static final String MESSAGE = "Número inválido";

        public InvalidNumberException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidPasswordException extends CardException
    {
        static final String MESSAGE = "Senha inválida";

        public InvalidPasswordException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidValidityException extends CardException
    {
        static final String MESSAGE = "Validade inválida";

        public InvalidValidityException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidCvvException extends CardException
    {
        static final String MESSAGE = "Cvv inválido";

        public InvalidCvvException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidAnnualFeeException extends CardException
    {
        static final String MESSAGE = "Anuidade inválida";

        public InvalidAnnualFeeException()
        {
            super(MESSAGE);
        }
    }
}

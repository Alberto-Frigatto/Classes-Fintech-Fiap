package com.services.exceptions;

import com.services.exceptions.CardExceptions.CardException;

public class CreditCardExceptions
{
    public static abstract class CreditCardException extends CardException
    {
        public CreditCardException(String message)
        {
            super(message);
        }
    }

    public static class invalidLimitException extends CreditCardException
    {
        static String MESSAGE = "\nLimite inválido\n";

        public invalidLimitException()
        {
            super(MESSAGE);
        }
    }
}

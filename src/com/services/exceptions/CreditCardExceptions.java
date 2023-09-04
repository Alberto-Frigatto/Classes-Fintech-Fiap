package com.services.exceptions;

import com.services.exceptions.CardExceptions.CardException;

public class CreditCardExceptions
{
    public static abstract class CreditCardException extends CardException
    {
        public CreditCardException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class invalidLimitException extends CreditCardException
    {
        static final String MESSAGE = "Limite inválido";

        public invalidLimitException()
        {
            super(MESSAGE);
        }
    }
}

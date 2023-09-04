package com.services.exceptions;

import com.services.exceptions.CardExceptions.CardException;

public class DebitCardExceptions
{
    public static abstract class DebitCardException extends CardException
    {
        public DebitCardException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class invalidDailyLimitException extends DebitCardException
    {
        static final String MESSAGE = "\nLimite diário inválido\n";

        public invalidDailyLimitException()
        {
            super(MESSAGE);
        }
    }
}

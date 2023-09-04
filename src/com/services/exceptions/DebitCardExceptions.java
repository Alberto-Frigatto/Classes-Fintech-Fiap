package com.services.exceptions;

import com.services.exceptions.CardExceptions.CardException;

public class DebitCardExceptions
{
    public static abstract class DebitCardException extends CardException
    {
        public DebitCardException(String message)
        {
            super(message);
        }
    }

    public static class invalidDailyLimitException extends DebitCardException
    {
        static String MESSAGE = "\nLimite diário inválido\n";

        public invalidDailyLimitException()
        {
            super(MESSAGE);
        }
    }
}

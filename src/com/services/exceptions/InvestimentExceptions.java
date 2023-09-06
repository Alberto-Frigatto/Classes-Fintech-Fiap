package com.services.exceptions;

import com.services.exceptions.ServiceExceptions.ServiceException;

public class InvestimentExceptions
{
    public static abstract class InvestimentException extends ServiceException
    {
        public InvestimentException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidkindAccountException extends InvestimentException
    {
        static final String MESSAGE = "Tipo de conta inválido";

        public InvalidkindAccountException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidWaitingPeriodException extends InvestimentException
    {
        static final String MESSAGE = "Carência inválida";

        public InvalidWaitingPeriodException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidInvestedAmountException extends InvestimentException
    {
        static final String MESSAGE = "Valor investido inválido";

        public InvalidInvestedAmountException()
        {
            super(MESSAGE);
        }
    }
}

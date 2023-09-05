package com.services.exceptions;

import com.services.exceptions.ServiceExceptions.ServiceException;

public class LoanExceptions
{
    public static abstract class LoanException extends ServiceException
    {
        public LoanException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidValueException extends LoanException
    {
        static final String MESSAGE = "Valor de empréstimo inválido";

        public InvalidValueException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidPortionsException extends LoanException
    {
        static final String MESSAGE = "Quantidade de parcelas inválida";

        public InvalidPortionsException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidEndDateException extends LoanException
    {
        static final String MESSAGE = "Data de término do empréstimo inválida";

        public InvalidEndDateException()
        {
            super(MESSAGE);
        }
    }
}
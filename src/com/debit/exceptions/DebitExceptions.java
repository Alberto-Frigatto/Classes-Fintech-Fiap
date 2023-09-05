package com.debit.exceptions;

import com.services.exceptions.ServiceExceptions.ServiceException;

public class DebitExceptions {
    public static abstract class DebitException extends ServiceException {
        public DebitException(String message) {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidValueException extends DebitException {
        static final String MESSAGE = "Valor inválido";

        public InvalidValueException() {
            super(MESSAGE);
        }
    }

    public static class InvalidPayeeException extends DebitException {
        static final String MESSAGE = "Destinatário inválido";

        public InvalidPayeeException() {
            super(MESSAGE);
        }
    }

    public static class InvalidDayException extends DebitException {
        static final String MESSAGE = "Dia inválido";

        public InvalidDayException() {
            super(MESSAGE);
        }
    }

    public static class InvalidPeriodException extends DebitException {
        static final String MESSAGE = "Período inválido";

        public InvalidPeriodException() {
            super(MESSAGE);
        }
    }

}

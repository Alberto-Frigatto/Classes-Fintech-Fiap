package com.services.exceptions;

import com.services.exceptions.ServiceExceptions.ServiceException;

public class PaymentExceptions {
    public static abstract class PaymentException extends ServiceException {
        public PaymentException(String message) {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidValueException extends PaymentException {
        static final String MESSAGE = "Valor de pagamento inválido";

        public InvalidValueException() {
            super(MESSAGE);
        }
    }

    public static class InvalidDueDateException extends PaymentException {
        static final String MESSAGE = "Data de vencimento inválida";

        public InvalidDueDateException() {
            super(MESSAGE);
        }
    }

    public static class InvalidBarCodeException extends PaymentException {
        static final String MESSAGE = "Código de barras inválido";

        public InvalidBarCodeException() {
            super(MESSAGE);
        }
    }

    public static class InvalidFeesException extends PaymentException {
        static final String MESSAGE = "Juros inválido";

        public InvalidFeesException() {
            super(MESSAGE);
        }
    }

    public static class InvalidNameException extends PaymentException {
        static final String MESSAGE = "Nome inválido";

        public InvalidNameException() {
            super(MESSAGE);
        }
    }

    public static class InvalidPayeeException extends PaymentException {
    static final String MESSAGE = "Destinatário inválido";

        public InvalidPayeeException() {
            super(MESSAGE);
        }
    }
}

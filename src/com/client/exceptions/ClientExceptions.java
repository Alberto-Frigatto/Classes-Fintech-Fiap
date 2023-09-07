package com.client.exceptions;

public class ClientExceptions
{
    public static abstract class ClientException extends Exception
    {
        public ClientException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidNameException extends ClientException
    {
        static final String MESSAGE = "Nome inválido";

        public InvalidNameException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidCepException extends ClientException
    {
        static final String MESSAGE = "CEP inválido";

        public InvalidCepException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidAddressException extends ClientException
    {
        static final String MESSAGE = "Endereço inválido";

        public InvalidAddressException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidPhoneException extends ClientException
    {
        static final String MESSAGE = "Telefone inválido";

        public InvalidPhoneException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidEmailException extends ClientException
    {
        static final String MESSAGE = "Email inválido";

        public InvalidEmailException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidPasswordException extends ClientException
    {
        static final String MESSAGE = "Senha inválida";

        public InvalidPasswordException()
        {
            super(MESSAGE);
        }
    }
}

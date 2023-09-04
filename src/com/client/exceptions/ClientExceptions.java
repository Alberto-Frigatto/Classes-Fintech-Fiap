package com.client.exceptions;

public class ClientExceptions
{
    public static abstract class ClientException extends Exception
    {
        public ClientException(String message)
        {
            super(message);
        }
    }

    public static class InvalidNameException extends ClientException
    {
        static String MESSAGE = "\nNome inválido\n";

        public InvalidNameException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidAddressException extends ClientException
    {
        static String MESSAGE = "\nEndereço inválido\n";

        public InvalidAddressException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidPhoneException extends ClientException
    {
        static String MESSAGE = "\nTelefone inválido\n";

        public InvalidPhoneException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidEmailException extends ClientException
    {
        static String MESSAGE = "\nEmail inválido\n";

        public InvalidEmailException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidPasswordException extends ClientException
    {
        static String MESSAGE = "\nSenha inválida\n";

        public InvalidPasswordException()
        {
            super(MESSAGE);
        }
    }
}

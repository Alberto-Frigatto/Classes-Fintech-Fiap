package com.client.exceptions;

import com.client.exceptions.ClientExceptions.ClientException;

public class PhysicalClientExceptions
{
    public abstract static class PhysicalClientException extends ClientException
    {
        public PhysicalClientException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidCpfException extends PhysicalClientException
    {
        static final String MESSAGE = "CPF inválido";

        public InvalidCpfException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidBirthDateException extends PhysicalClientException
    {
        static final String MESSAGE = "Data de nascimento inválida";

        public InvalidBirthDateException()
        {
            super(MESSAGE);
        }
    }
}

package com.client.exceptions;

import com.client.exceptions.ClientExceptions.ClientException;

public class PhysicalClientExceptions
{
    public abstract static class PhysicalClientException extends ClientException
    {
        public PhysicalClientException(String message)
        {
            super(message);
        }
    }

    public static class InvalidCpfException extends PhysicalClientException
    {
        static String MESSAGE = "\nCPF inválido\n";

        public InvalidCpfException()
        {
            super(MESSAGE);
        }
    }

    public static class InvalidBirthDateException extends PhysicalClientException
    {
        static String MESSAGE = "\nData de nascimento inválida\n";

        public InvalidBirthDateException()
        {
            super(MESSAGE);
        }
    }
}

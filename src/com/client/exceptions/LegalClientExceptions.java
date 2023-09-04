package com.client.exceptions;

import com.client.exceptions.ClientExceptions.ClientException;

public class LegalClientExceptions
{
    public abstract static class LegalClientException extends ClientException
    {
        public LegalClientException(String message)
        {
            super(message);
        }
    }

    public static class InvalidCnpjException extends LegalClientException
    {
        static String MESSAGE = "\nCNPJ inválido\n";

        public InvalidCnpjException()
        {
            super(MESSAGE);
        }
    }
}

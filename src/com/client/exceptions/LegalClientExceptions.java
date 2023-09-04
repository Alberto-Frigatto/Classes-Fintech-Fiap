package com.client.exceptions;

import com.client.exceptions.ClientExceptions.ClientException;

public class LegalClientExceptions
{
    public abstract static class LegalClientException extends ClientException
    {
        public LegalClientException(String message)
        {
            super("\n" + message + "\n");
        }
    }

    public static class InvalidCnpjException extends LegalClientException
    {
        static final String MESSAGE = "CNPJ inválido";

        public InvalidCnpjException()
        {
            super(MESSAGE);
        }
    }
}

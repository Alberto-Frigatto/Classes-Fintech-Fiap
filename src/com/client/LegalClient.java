package com.client;

import com.client.exceptions.LegalClientExceptions;
import com.client.exceptions.ClientExceptions.ClientException;
import com.legalPerson.LegalPerson;

public class LegalClient extends Client
{
    private String cnpj;

    public LegalClient(
        String name,
        String address,
        String phone1,
        String phone2,
        String email,
        String password,
        String cnpj
    ) throws ClientException
    {
        super(name, address, phone1, phone2, email, password, Client.KindPerson.PJ);

        this.cnpj = cnpj.strip();

        this.validateCnpj();
    }

    private void validateCnpj() throws LegalClientExceptions.InvalidCnpjException
    {
        if (!LegalPerson.cnpjIsValid(this.cnpj))
            throw new LegalClientExceptions.InvalidCnpjException();
    }

    public String getCnpj()
    {
        return this.cnpj;
    }
}

package com.client;

import java.time.LocalDate;

import com.client.exceptions.ClientExceptions.ClientException;
import com.client.exceptions.PhysicalClientExceptions;

public class PhysicalClient extends Client
{
    private String cpf;
    private LocalDate birthDate;

    public PhysicalClient(
        String name,
        String address,
        String phone1,
        String phone2,
        String email,
        String password,
        String cpf,
        String birthDate
    ) throws ClientException
    {
        super(name, address, phone1, phone2, email, password, Client.KindPerson.PF);

        this.cpf = cpf.strip();

        this.validateCpf();
        this.setBirthDateIfValid(birthDate);
    }

    private void validateCpf() throws PhysicalClientExceptions.InvalidCpfException
    {
        if (!this.cpfIsValid())
            throw new PhysicalClientExceptions.InvalidCpfException();
    }

    private boolean cpfIsValid()
    {
        if (this.cpf.matches("(\\d)\\1{10}") || this.cpf.strip().length() != 11)
            return false;

        int[] cpfDigits = new int[11];

        for (int i = 0; i < 11; i++)
            cpfDigits[i] = Character.getNumericValue(this.cpf.charAt(i));

        for (int t = 9; t < 11; t++)
        {
            int d = 0;
            for (int c = 0; c < t; c++)
                d += cpfDigits[c] * ((t + 1) - c);

            d = ((10 * d) % 11) % 10;

            if (cpfDigits[t] != d)
                return false;
        }

        return true;
    }

    private void setBirthDateIfValid(String birthDate) throws PhysicalClientExceptions.InvalidBirthDateException
    {
        if (!this.birthDateIsValid(birthDate))
            throw new PhysicalClientExceptions.InvalidBirthDateException();

        this.birthDate = LocalDate.parse(birthDate);

        if (!this.isOfLegalAge(this.birthDate))
            throw new PhysicalClientExceptions.InvalidBirthDateException();
    }

    private boolean birthDateIsValid(String birthDate)
    {
        return LocalDate.parse(birthDate).isBefore(LocalDate.now());
    }

    private boolean isOfLegalAge(LocalDate birthDate)
    {
        LocalDate legalAgeDate = LocalDate.now().minusYears(18);
        return birthDate.isBefore(legalAgeDate);
    }

    public String getCpf()
    {
        return this.cpf;
    }

    public LocalDate getBirthDate()
    {
        return this.birthDate;
    }
}

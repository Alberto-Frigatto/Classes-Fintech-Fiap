package com.legalPerson;

public class LegalPerson
{
    static public boolean cnpjIsValid(String cnpj)
    {
        if (cnpj.matches("(\\d)\\1{13}") || cnpj.strip().length() != 14)
            return false;

        int[] cnpjDigits = new int[14];
        for (int i = 0; i < 14; i++)
            cnpjDigits[i] = Character.getNumericValue(cnpj.charAt(i));

        int[] weights = {5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};
    
        int sum = 0;
        for (int i = 0; i < 12; i++)
            sum += cnpjDigits[i] * weights[i];

        int rest = sum % 11;
        int expectedDigit = (rest < 2) ? 0 : 11 - rest;
        if (cnpjDigits[12] != expectedDigit)
            return false;

        weights = new int[]{6, 5, 4, 3, 2, 9, 8, 7, 6, 5, 4, 3, 2};

        sum = 0;
        for (int i = 0; i < 13; i++)
            sum += cnpjDigits[i] * weights[i];

        rest = sum % 11;
        expectedDigit = (rest < 2) ? 0 : 11 - rest;
        return cnpjDigits[13] == expectedDigit;
    }
}
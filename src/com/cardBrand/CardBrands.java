package com.cardBrand;

import com.cardBrand.exceptions.CardBrandExceptions.CardBrandException;

public enum CardBrands
{
    VISA("Visa", "31551765000143", 16, 3),
    ElO("Elo", "09227084000175", 16, 3),
    AMEX("American Express", "59438325000101", 15, 4),
    DINERS_CLUB("Dinners Club", "54419627000100", 14, 3),
    DISCOVER("Discover", "06083435000123", 16, 4),
    MASTERCARD("Mastercard", "08155137000127", 16, 3);

    private CardBrand instance;

    CardBrands(String name, String cnpj, int numberLength, int cvvLength)
    {
        try
        {
            this.instance = new CardBrand(name, cnpj, numberLength, cvvLength);
        }
        catch (CardBrandException e)
        {
            System.err.println(e.getMessage());
        }
    }

    public CardBrand getInstance()
    {
        return this.instance;
    }
}

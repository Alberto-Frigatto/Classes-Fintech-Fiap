package com.services.card;

import com.bankAccounts.BankAccount;
import com.cardBrand.CardBrand;
import com.services.exceptions.ServiceExceptions.ServiceException;
import com.services.exceptions.CreditCardExceptions;

public class CreditCard extends Card
{
    private float limit;

    public CreditCard(
        float serviceFee,
        BankAccount bankAccount,
        CardBrand cardBrand,
        String number,
        String password,
        String validity,
        String cvv,
        float annualFee,
        float limit
    ) throws ServiceException
    {
        super(serviceFee, bankAccount, Card.KindCard.CREDITO, cardBrand, number, password, validity, cvv, annualFee);

        this.limit = limit;

        this.validateLimit();
    }

    private void validateLimit() throws CreditCardExceptions.invalidLimitException
    {
        if (!this.limitIsValid())
            throw new CreditCardExceptions.invalidLimitException();
    }

    private boolean limitIsValid()
    {
        return this.limit > 0 || this.limit <= 1e6;
    }

    public float getLimit()
    {
      return this.limit;
    }
}

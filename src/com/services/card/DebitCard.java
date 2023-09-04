package com.services.card;

import com.bankAccounts.BankAccount;
import com.cardBrand.CardBrand;
import com.services.exceptions.DebitCardExceptions;
import com.services.exceptions.ServiceExceptions.ServiceException;;

public class DebitCard extends Card
{
    private double dailyLimit;

    public DebitCard(
        float serviceFee,
        BankAccount bankAccount,
        CardBrand cardBrand,
        String number,
        String password,
        String validity,
        String cvv,
        float annualFee,
        double dailyLimit
    ) throws ServiceException
    {
        super(serviceFee, bankAccount, Card.KindCard.DEBITO, cardBrand, number, password, validity, cvv, annualFee);

        this.dailyLimit = dailyLimit;

        this.validateDailyLimit();
    }

    private void validateDailyLimit() throws DebitCardExceptions.invalidDailyLimitException
    {
        if (!this.dailyLimitIsvalid())
            throw new DebitCardExceptions.invalidDailyLimitException();
    }

    private boolean dailyLimitIsvalid()
    {
        return this.dailyLimit > 0 || this.dailyLimit <= 1e4;
    }

    public double getDailyLimit()
    {
      return this.dailyLimit;
    }
}

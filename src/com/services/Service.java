package com.services;

import com.bankAccounts.BankAccount;
import com.services.exceptions.ServiceExceptions;
import com.services.exceptions.ServiceExceptions.ServiceException;

public abstract class Service
{
    private KindService kindService;
    private float serviceFee;
    private boolean isActive = true;
    private BankAccount bankAccount;

    public Service(KindService kindService, float serviceFee, BankAccount bankAccount) throws ServiceException
    {
        this.kindService = kindService;
        this.serviceFee = serviceFee;
        this.bankAccount = bankAccount;

        this.validateServiceFee();
    }

    public static enum KindService
    {
        LOAN,
        INVESTIMENT,
        CARD,
        PAYMENT
    }

    private void validateServiceFee() throws ServiceExceptions.InvalidServiceFeeException
    {
        if (!this.serviceFeeIsValid())
            throw new ServiceExceptions.InvalidServiceFeeException();
    }
    
    private boolean serviceFeeIsValid()
    {
        return this.serviceFee >= 0 && this.serviceFee <= 500;
    }

    public KindService getKindService()
    {
        return this.kindService;
    }

    public float getServiceFee()
    {
        return this.serviceFee;
    }

    public boolean getIsActive()
    {
        return this.isActive;
    }

    public BankAccount getBankAccount()
    {
        return this.bankAccount;
    }
}
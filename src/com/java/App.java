package com.java;

import com.bankAccounts.CheckingAccount;
import com.bankAccounts.SavingsAccount;
import com.bankAccounts.exceptions.BankAccountExceptions.BankAccountException;
import com.cardBrand.CardBrands;
import com.client.LegalClient;
import com.client.PhysicalClient;
import com.client.exceptions.ClientExceptions.ClientException;
import com.services.Loan;
import com.services.card.CreditCard;
import com.services.exceptions.ServiceExceptions.ServiceException;
import com.bank.Banks;

public class App
{
    public static void main(String[] args) throws Exception
    {
        try
        {
            PhysicalClient alberto = new PhysicalClient(
                "Alberto Frigatto de Andrade Ferreira",
                "77015524",
                "Rua giulio ferro",
                "11994304367",
                null,
                "alberto@gmail.com",
                "Tius4012#",
                "63743248034",
                "2005-02-27"
            );

            LegalClient adriano = new LegalClient(
                "Adriano Lombardi",
                "88058498",
                "Rua carajaí dos santos",
                "12931451046",
                "12947589653",
                "adriano.lombardi@hotmail.com",
                "Aosi4517%$",
                "97336370000136"
            );

            CheckingAccount albertoAccount = new CheckingAccount(
                alberto,
                Banks.ITAU.getInstance(),
                "2310654",
                "120",
                1000
            );

            SavingsAccount adrianoAccount = new SavingsAccount(
                adriano,
                Banks.SAFRA.getInstance(),
                "1421534597",
                "4150"
            );

            albertoAccount.deposit(8e4);
            albertoAccount.draft(350);

            adrianoAccount.deposit(6e3);

            albertoAccount.transfer(500, adrianoAccount);

            adrianoAccount.applyMonthlyIncome();

            Loan adrianoLoan = new Loan(
                .12f,
                adrianoAccount,
                1.5e4,
                "2024-03-05"
            );
            adrianoLoan.payPortion();

            CreditCard albertoCard = new CreditCard(
                .014f,
                albertoAccount,
                CardBrands.MASTERCARD.getInstance(),
                "1234567898765432",
                "4512",
                "02/26",
                "123",
                300,
                2000
            );
            albertoCard.payAnnualFee();
            albertoCard.blockCard();

            adrianoAccount.cancelAccount();

            albertoAccount.includeOwner(new PhysicalClient(
                "Renato Brandão",
                "12455782",
                "Rua y",
                "1142563321",
                null,
                "renato@gmail.com",
                "14ERoo*(",
                "90914374052",
                "1989-08-15"
            ));

            albertoAccount.getData();
            adrianoAccount.getData();
        }
        catch (ClientException | BankAccountException | ServiceException e)
        {
            System.err.println(e.getMessage());
        }
    }
}

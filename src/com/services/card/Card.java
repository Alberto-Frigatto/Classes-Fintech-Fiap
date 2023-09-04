package com.services.card;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.services.exceptions.CardExceptions;
import com.services.exceptions.ServiceExceptions.ServiceException;
import com.services.Service;
import com.bankAccounts.BankAccount;
import com.cardBrand.CardBrand;

public abstract class Card extends Service
{
    private KindCard kindCard;
    private CardBrand cardBrand;
    private String number;
    private String password;
    private LocalDate validity;
    private String cvv;
    private float monthlyLimit;
    private float annualFee;
    private boolean isActive = true;

    public Card(
        float serviceFee,
        BankAccount bankAccount,
        KindCard kindCard,
        CardBrand cardBrand,
        String number,
        String password,
        String validity,
        String cvv,
        float annualFee
    ) throws ServiceException
    {
        super(Service.KindService.CARD, serviceFee, bankAccount);

        this.kindCard = kindCard;
        this.cardBrand = cardBrand;
        this.number = number.strip();
        this.password = password.strip();
        this.cvv = cvv.strip();
        this.annualFee = annualFee;

        this.validateNumber();
        this.processPasswordIfValid();
        this.setValidityIfValid(validity);
        this.validateCvv();
        this.validateAnnualFee();
    }

    public static enum KindCard
    {
        CREDITO,
        DEBITO
    }

    private void validateNumber() throws CardExceptions.InvalidNumberException
    {
        if (!this.numberIsValid())
            throw new CardExceptions.InvalidNumberException();
    }

    private boolean numberIsValid()
    {
        if (this.number.isEmpty() || this.number.length() != this.cardBrand.getNumberLength())
            return false;

        for (char c : this.number.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private void processPasswordIfValid() throws CardExceptions.InvalidPasswordException
    {
        if (!this.passwordIsValid())
            throw new CardExceptions.InvalidPasswordException();

        this.password = hashPassword(this.password);
    }

    private boolean passwordIsValid()
    {
        int maxLength = 6;
        int minLength = 4;

        if (this.password.isEmpty() || this.password.length() < minLength || this.password.length() > maxLength)
            return false;

        for (char c : this.password.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private String hashPassword(String password)
    {
        try
        {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder stringBuilder = new StringBuilder();
            for (byte b : hashedBytes)
                stringBuilder.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));

            return stringBuilder.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException("Error hashing password.");
        }
    }

    private void setValidityIfValid(String validity) throws CardExceptions.InvalidValidityException
    {
        if (!this.validityIsValid(validity))
            throw new CardExceptions.InvalidValidityException();

        Pattern pattern = Pattern.compile("^(0[1-9]|1[0-2])/(\\d{2})$");
        Matcher matcher = pattern.matcher(validity);
        matcher.matches();

        int month = Integer.parseInt(matcher.group(1));
        int year = Integer.parseInt(matcher.group(2)) + 2000;

        this.validity = LocalDate.of(year, month, 1);
    }

    private boolean validityIsValid(String validity)
    {
        Pattern pattern = Pattern.compile("^(0[1-9]|1[0-2])/(\\d{2})$");
        Matcher matcher = pattern.matcher(validity);
        
        if (!matcher.matches())
            return false;

        int month = Integer.parseInt(matcher.group(1));
        int year = Integer.parseInt(matcher.group(2)) + 2000;

        return LocalDate.of(year, month, 1).isAfter(LocalDate.now());
    }

    private void validateCvv() throws CardExceptions.InvalidCvvException
    {
        if (!this.cvvIsValid())
            throw new CardExceptions.InvalidCvvException();
    }

    private boolean cvvIsValid()
    {
        if (this.cvv.isEmpty() || this.cvv.length() != this.cardBrand.getCvvLength())
            return false;

        for (char c : this.cvv.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private void validateAnnualFee() throws CardExceptions.InvalidAnnualFeeException
    {
        if (!this.annualFeeIsValid())
            throw new CardExceptions.InvalidAnnualFeeException();
    }
    
    private boolean annualFeeIsValid()
    {
        return this.annualFee >= 0 && this.annualFee <= 5e2;
    }

    public void blockCard()
    {
        if (this.isActive)
            this.isActive = false;
    }

    public KindCard getKindCard()
    {
      return this.kindCard;
    }

    public CardBrand getCardBrand() 
    {
      return this.cardBrand;
    }

    public String getNumber() 
    {
      return this.number;
    }

    public String getPassword() 
    {
      return this.password;
    }

    public LocalDate getValidity() 
    {
      return this.validity;
    }

    public String getCvv() 
    {
      return this.cvv;
    }

    public float getMonthlyLimit() 
    {
      return this.monthlyLimit;
    }

    public float getAnnualFee()
    {
      return this.annualFee;
    }

    public boolean getIsActive()
    {
      return this.isActive;
    }
}

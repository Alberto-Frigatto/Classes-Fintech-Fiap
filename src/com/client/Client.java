package com.client;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.client.exceptions.ClientExceptions;
import com.client.exceptions.ClientExceptions.ClientException;

public abstract class Client
{
    private String name;
    private String cep;
    private String address;
    private String phone1;
    private String phone2;
    private String email;
    private String password;
    private KindPerson kindPerson;
    private boolean isActive = true;

    public Client(
        String name,
        String cep,
        String address,
        String phone1,
        String phone2,
        String email,
        String password,
        KindPerson kindPerson
    ) throws ClientException
    {
        this.name = name.strip();
        this.cep = cep.strip();
        this.address = address.strip();
        this.phone1 = phone1.strip();
        this.phone2 = phone2 != null ? phone2.strip() : phone2;
        this.email = email.strip();
        this.password = password.strip();
        this.kindPerson = kindPerson;

        this.validateName();
        this.validateCep();
        this.validateAddress();
        this.vaidatePhone1();
        this.validateEmail();
        this.processPasswordIfValid();

        if (this.phone2 instanceof String || this.phone2 != null)
            this.validatePhone2();
    }

    static public enum KindPerson
    {
        PF,
        PJ
    }

    private void validateName() throws ClientExceptions.InvalidNameException
    {
        if (!this.nameIsValid())
            throw new ClientExceptions.InvalidNameException();
    }

    private boolean nameIsValid()
    {
        return this.name instanceof String && !this.name.isEmpty() && this.name.length() <= 70 && this.name.matches("^[^0-9]*$");
    }

    private void validateCep() throws ClientExceptions.InvalidCepException
    {
        if (!this.cepIsValid())
            throw new ClientExceptions.InvalidCepException();
    }

    private boolean cepIsValid()
    {
        int cepLength = 8;

        if (this.cep.isEmpty() || this.cep.length() != cepLength)
            return false;

        for (char c : this.cep.toCharArray())
            if (!Character.isDigit(c))
                return false;

        return true;
    }

    private void validateAddress() throws ClientExceptions.InvalidAddressException
    {
        if (!this.addressIsValid())
            throw new ClientExceptions.InvalidAddressException();
    }

    private boolean addressIsValid()
    {
        return this.address instanceof String && !this.address.isEmpty() && this.address.length() <= 100;
    }

    private void vaidatePhone1() throws ClientExceptions.InvalidPhoneException
    {
        if (!this.phoneIsValid(this.phone1))
            throw new ClientExceptions.InvalidPhoneException();
    }

    private void validatePhone2() throws ClientExceptions.InvalidPhoneException
    {
        if (!this.phoneIsValid(this.phone2))
            throw new ClientExceptions.InvalidPhoneException();
    }

    private boolean phoneIsValid(String phone)
    {
        return isValidFixedPhone(phone) || isValidMobilePhone(phone);
    }

    private boolean isValidFixedPhone(String phone)
    {
        Pattern fixedPattern = Pattern.compile("^\\d{10}$");
        Matcher fixedMatcher = fixedPattern.matcher(phone);

        return fixedMatcher.matches() && !isRepeatingOrSequential(phone);
    }

    private boolean isValidMobilePhone(String phone)
    {
        Pattern mobilePattern = Pattern.compile("^[1-9]{2}9\\d{8}$");
        Matcher mobileMatcher = mobilePattern.matcher(phone);

        return mobileMatcher.matches() && !isRepeatingOrSequential(phone);
    }

    private boolean isRepeatingOrSequential(String phone) {
        if (phone.matches("(\\d)\\1+"))
            return true;

        String sequentialPattern = "01234567890123456789";

        return sequentialPattern.contains(phone);
    }

    private void validateEmail() throws ClientExceptions.InvalidEmailException
    {
        if (!this.emailIsValid())
            throw new ClientExceptions.InvalidEmailException();
    }

    private boolean emailIsValid()
    {
        String emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9]+\\.)+[A-Za-z]{2,}$";

        return this.email.matches(emailRegex);
    }

    private void processPasswordIfValid() throws ClientExceptions.InvalidPasswordException
    {
        if (!this.passwordIsValid())
            throw new ClientExceptions.InvalidPasswordException();

        this.password = hashPassword(this.password);
    }

    private boolean passwordIsValid()
    {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!_\\-()&¨%'\"<>:;/?~^{}´`]).{8,}$";

        return this.password.matches(passwordRegex);
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

    public void deleteAccount()
    {
        if (this.isActive)
            this.isActive = false;
    }

    public void updateAccount(
        String name,
        String address,
        String phone,
        String email,
        String password
    ) throws ClientException
    {
        this.name = name;
        this.address = address;
        this.phone1 = phone;
        this.email = email;
        this.password = password;

        this.validateName();
        this.validateAddress();
        this.vaidatePhone1();
        this.validateEmail();
        this.processPasswordIfValid();

        if (this.phone2 instanceof String || this.phone2 != null)
            this.validatePhone2();
    }

    public String getName()
    {
        return this.name;
    }

    public String getAddress()
    {
        return this.address;
    }

    public String getPhone1()
    {
        return this.phone1;
    }

    public String getPhone2()
    {
        return this.phone2;
    }

    public String getEmail()
    {
        return this.email;
    }

    public String getPassword()
    {
        return this.password;
    }

    public KindPerson getkindPerson()
    {
        return this.kindPerson;
    }

    public boolean getIsActive()
    {
        return this.isActive;
    }
}

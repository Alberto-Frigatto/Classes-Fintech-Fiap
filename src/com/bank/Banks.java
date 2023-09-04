package com.bank;

import com.bank.exceptions.BankExceptions.BankException;

public enum Banks
{
    BRADESCO("Bradesco", "60746948000112", "237"),
    CAIXA("Caixa", "00360305000104", "104"),
    ITAU("Itaú", "60701190000104", "341"),
    SANTANDER("Santander", "90400888000142", "033"),
    NUBANK("Nubank", "24410913000144", "260"),
    C6("C6 Bank", "31872495000172", "336"),
    MERCADO_PAGO("Mercado Pago", "10573521000191", "323"),
    PICPAY("PicPay", "22896431000110", "380"),
    CARREFOUR("Carrefour", "08357240000150", "368"),
    CREFISA("Crefisa", "60779196000196", "069"),
    INTER("Inter", "00416968000101", "077"),
    JP_MORGAN("J. P. Morgan", "05624540000160", "376"),
    SAFRA("Safra", "58160789000128", "422"),
    BANCO_DO_BRASIL("Banco do Brasil", "00000000000191", "001");

    private Bank instance;

    Banks(String name, String cnpj, String number)
    {
        try {
            this.instance = new Bank(name, cnpj, number);
        } catch (BankException e) {
            System.err.println(e.getMessage());
        }
    }

    public Bank getInstance()
    {
        return this.instance;
    }
}

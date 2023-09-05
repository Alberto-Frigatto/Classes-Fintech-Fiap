package com.debit;

public interface AutomaticDebit
{
    public void includeAutomaticDebit(Debit debit);
    public void payAutomaticDebits();
}

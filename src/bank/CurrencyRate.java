package bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CurrencyRate {
    private String currencyType1;
    private String currencyType2;
    private double exchangeRate;

    /**
     * For creating currency rate.
     *
     * @param currencyType1 The currency type of which currency convert from.
     * @param currencyType2 The currency type of which currency convert to.
     * @param exchangeRate  The rate of the exchanging process.
     */
    public CurrencyRate(String currencyType1, String currencyType2, double exchangeRate) {
        this.currencyType1 = currencyType1;
        this.currencyType2 = currencyType2;
        this.exchangeRate = exchangeRate;
    }

    public String getCurrencyType1() {
        return currencyType1;
    }

    public String getCurrencyType2() {
        return currencyType2;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }

    public ArrayList<String> getCurrencies() {
        return new ArrayList<>(Arrays.asList(this.currencyType1, this.currencyType2));
    }

    public void setExchangeRate(double exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return String.format("%s -> %s Rate: %s", this.currencyType1, this.currencyType2, this.exchangeRate);
    }
}

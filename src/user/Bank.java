package user;

import bank.CurrencyRate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Bank extends User {

    private List<String> currencies;
    private List<CurrencyRate> currencyRateList;

    private int availableFundId;
    private int availableStockId;

    //    private double currentRateTRYtoEUR;
//    private double currentRateTRYtoUSD;
//    private double currentRateTRYtoXAU;
//    private double currentRateEURtoUSD;
//    private double currentRateEURtoXAU;
//    private double currentRateUSDtoXAU;
    private List<Client> clients;

    public Bank() {
//        this.currentRateTRYtoEUR = 0.04;
//        this.currentRateTRYtoUSD = 0.05;
//        this.currentRateTRYtoXAU = 0.03;
//        this.currentRateEURtoUSD = 0.95;
//        this.currentRateEURtoXAU = 0.07;
//        this.currentRateUSDtoXAU = 0.36;
        this.availableStockId = 0;
        this.availableFundId = 0;
        this.clients = new ArrayList<>();
        this.currencies = new ArrayList<>(Arrays.asList("EUR", "TRY", "XAU", "USD"));
        this.currencyRateList = new ArrayList<>();
        for (String currency : this.currencies) {
            for (String currency2 : this.currencies) {
                if (!currency.equals(currency2)) {
                    CurrencyRate currencyRate = new CurrencyRate(currency, currency2, 0);
                    this.currencyRateList.add(currencyRate);
                }
            }
        }
    }

    public List<Client> getClients() {
        return clients;
    }

    public boolean checkClient(String name) {
        for (Client client : clients) {
            if (client.getClientName().equals(name)) {
                return false;
            }
        }
        return true;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public Client getClient(String name) {
        for (Client client : clients) {
            if (client.getClientName().equals(name)) {
                return client;
            }
        }
        return null;
    }

    public void displayClients() {
        for (Client client : clients) {
            System.out.println("Name: " + client.getClientName());
        }
    }


    /**
     * Find a currency rate in currencyRateList. If there is no result returns null.
     *
     * @param currencyType1 The currency type of which currency convert from.
     * @param currencyType2 The currency type of which currency convert to.
     * @return {@code CurrencyRate/null}
     */
    private CurrencyRate findCurrencyRate(String currencyType1, String currencyType2) {
        ArrayList<String> currencies = new ArrayList<>(Arrays.asList(currencyType1, currencyType2));
        for (CurrencyRate currencyRate : this.currencyRateList) {
            if (currencies.equals(currencyRate.getCurrencies())) {
                return currencyRate;
            }
        }
        return null;
    }

    /**
     * For creating currency rate.
     *
     * @param currencyType1 The currency type of which currency convert from.
     * @param currencyType2 The currency type of which currency convert to.
     * @param value         The rate of the exchanging process.
     */
    public double getCurrencyConvertedValue(String currencyType1, String currencyType2, double value) {
        CurrencyRate wantedCurrencyRate = findCurrencyRate(currencyType1, currencyType2);
        if (wantedCurrencyRate != null) {
            return value * wantedCurrencyRate.getExchangeRate();
        } else {
            return -1;
        }
    }

    /**
     * For set a valid currency rate. Returns the status of setting.
     *
     * @param currencyType1 The currency type of that wanted from.
     * @param currencyType2 The currency type of that wanted to.
     * @param value         The rate of the exchanging process.
     * @return {@code true} if currency rate finds and set.
     */
    public boolean setCurrencyRate(String currencyType1, String currencyType2, double value) {
        CurrencyRate wantedCurrencyRate = findCurrencyRate(currencyType1, currencyType2);
        if (wantedCurrencyRate != null) {
            wantedCurrencyRate.setExchangeRate(value);
            return true;
        } else {
            return false;
        }
    }
}

package user;

import account.*;
import bank.CurrencyRate;
import bank.Fund;
import bank.InterestRate;
import bank.Stock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Bank extends User {

    private List<Fund> funds;
    private List<Stock> stocks;
    private List<InterestRate> interestRates;

    private List<String> currencies;
    private List<CurrencyRate> currencyRates;

    private int availableFundId;
    private int availableStockId;

    private List<Client> clients;

    public double getRandomDoubleValue() {
        Random random = new Random();
        return random.nextDouble();
    }

    public Bank() {
        this.availableStockId = 0;
        this.availableFundId = 0;
        this.funds = new ArrayList<>();
        this.stocks = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.currencies = new ArrayList<>(Arrays.asList("EUR", "TRY", "XAU", "USD"));
        this.interestRates = new ArrayList<>();
        this.currencyRates = new ArrayList<>();

        // #######
        // ALL VALUES HAS RANDOM VALUE FOR TESTING, YOU CAN REPLACE THEM WITH 0 OR ANY INITIAL VALUE
        // #######
        for (String currency : this.currencies) {

            InterestRate interestRate = new InterestRate(currency, getRandomDoubleValue());
            this.interestRates.add(interestRate);

            // SAME CURRENCY TO SAME CURRENCY
            CurrencyRate currencyRate = new CurrencyRate(currency, currency, 1);
            this.currencyRates.add(currencyRate);

            for (String currency2 : this.currencies) {
                if (!currency.equals(currency2)) {
                    CurrencyRate innerCurrencyRate = new CurrencyRate(currency, currency2, getRandomDoubleValue());
                    this.currencyRates.add(innerCurrencyRate);
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

    public void displayCurrencies() {
        for (String currency : this.currencies) {
            System.out.println("Currency: " + currency);
        }
    }

    public void displayCurrenciesWithFilter(String filter) {
        for (String currency : this.currencies) {
            if (!currency.equals(filter)) {
                System.out.println("Currency: " + currency);
            }
        }
    }

    public void displayInterestRate() {
        for (InterestRate interestRate :
                this.interestRates) {
            System.out.printf("%s Interest Rate: %s", interestRate.getCurrencyType(), interestRate.getInterestRate());
        }
    }


    /**
     * Find a currency rate in currencyRateList. If there is no result returns null.
     *
     * @param currencyType1 The currency type of which currency convert from.
     * @param currencyType2 The currency type of which currency convert to.
     * @return {@code CurrencyRate/null}
     */
    public CurrencyRate findCurrencyRate(String currencyType1, String currencyType2) {
        ArrayList<String> currencies = new ArrayList<>(Arrays.asList(currencyType1, currencyType2));
        for (CurrencyRate currencyRate : this.currencyRates) {
            if (currencies.equals(currencyRate.getCurrencies())) {
                return currencyRate;
            }
        }
        return null;
    }

    /**
     * For currency converting.
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
     * It is used to get the converted value of a component. If the component is a group, the values of the accounts of the group are collected cumulatively.
     *
     * @param accountComponent The component whose value is desired to be found.
     * @param currencyRate     The currency type of which currency convert to.
     * @return {@code double}  The converted value.
     */
    public double getComponentToConvertedValue(AccountComponent accountComponent, String currencyRate) {
        if (accountComponent instanceof Account) {
            return this.getCurrencyConvertedValue(accountComponent instanceof CurrencyAccount ? ((CurrencyAccount) accountComponent).getType() : "TRY", currencyRate, ((Account) accountComponent).getValue());
        } else {
            double totalValue = 0;
            for (Account account : ((AccountGroup) accountComponent).getAllAccounts()) {
                totalValue += this.getCurrencyConvertedValue(account.getType(), currencyRate, account.getValue());
            }
            return totalValue;
        }
    }

    /**
     * For set a valid currency rate, same currency rates are invalid situation. Returns the status of setting.
     *
     * @param currencyType1 The currency type of that wanted from.
     * @param currencyType2 The currency type of that wanted to.
     * @param value         The rate of the exchanging process.
     * @return {@code true} if currency rate finds and set.
     */
    public boolean setCurrencyRate(String currencyType1, String currencyType2, double value) {
        if (currencyType1.equals(currencyType2)) {
            return false;
        } else {
            CurrencyRate wantedCurrencyRate = findCurrencyRate(currencyType1, currencyType2);
            if (wantedCurrencyRate != null) {
                wantedCurrencyRate.setExchangeRate(value);
                return true;
            } else {
                return false;
            }
        }
    }


    public void createStock(String name, double price) {
        this.stocks.add(new Stock(availableStockId, name, price));
        this.availableStockId++;
    }

    public void createFund(String name, double price) {
        this.funds.add(new Fund(availableFundId, name, price));
        this.availableFundId++;
    }

    public List<Fund> getFunds() {
        return funds;
    }

    public List<Stock> getStocks() {
        return stocks;
    }
}

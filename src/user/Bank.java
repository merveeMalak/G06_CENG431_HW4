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
        this.availableStockId = 1;
        this.availableFundId = 1;
        this.funds = new ArrayList<>();
        this.stocks = new ArrayList<>();
        this.clients = new ArrayList<>();
        this.currencies = new ArrayList<>(Arrays.asList("EUR", "TRY", "XAU", "USD"));
        this.interestRates = new ArrayList<>();
        this.currencyRates = new ArrayList<>();
        initializeBank();

    }

    private void initializeBank() {
        // #######
        // ALL VALUES HAS RANDOM VALUE FOR TESTING, YOU CAN REPLACE THEM WITH 0 OR ANY INITIAL VALUE
        // #######
        for (String currency : this.currencies) {

            InterestRate interestRate = new InterestRate(currency, 0.05);
            this.interestRates.add(interestRate);

            // SAME CURRENCY TO SAME CURRENCY
            CurrencyRate currencyRate = new CurrencyRate(currency, currency, 1);
            this.currencyRates.add(currencyRate);

            for (String currency2 : this.currencies) {
                if (!currency.equals(currency2)) {
                    CurrencyRate innerCurrencyRate = new CurrencyRate(currency, currency2, 0);
                    this.currencyRates.add(innerCurrencyRate);
                }
            }
            setCurrencyRate("TRY", "EUR", 0.24);
            setCurrencyRate("TRY", "USD", 0.25);
            setCurrencyRate("TRY", "XAU", 0.20);
            setCurrencyRate("EUR", "USD", 0.9);
            setCurrencyRate("EUR", "XAU", 0.70);
            setCurrencyRate("USD", "XAU", 0.85);

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

    public void displayCurrencyRates() {
        for (CurrencyRate currencyRate :
                this.currencyRates) {
            System.out.println(currencyRate);
        }
    }

    public void displayInterestRate() {
        for (InterestRate interestRate :
                this.interestRates) {
            System.out.printf("%s Interest Rate: %s\n", interestRate.getCurrencyType(), interestRate.getInterestRate());
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
        for (CurrencyRate currencyRate : this.currencyRates) {
            if (currencyType1.equals(currencyRate.getCurrencyType1()) && currencyType2.equals(currencyRate.getCurrencyType2())) {
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
            CurrencyRate wantedCurrencyRateFirst = findCurrencyRate(currencyType1, currencyType2);
            CurrencyRate wantedCurrencyRateSecond = findCurrencyRate(currencyType2, currencyType1);
            if (wantedCurrencyRateFirst != null && wantedCurrencyRateSecond != null) {
                wantedCurrencyRateFirst.setExchangeRate(value);
                wantedCurrencyRateSecond.setExchangeRate(1 / value);
                return true;
            } else {
                return false;
            }
        }
    }

    public boolean setInterestRate(String currency, double interestRateValue) {
        InterestRate interestRate = findInterestRate(currency);
        if (interestRate != null) {
            interestRate.setInterestRate(interestRateValue);
            return true;
        }
        return false;
    }

    private InterestRate findInterestRate(String currency) {
        for (InterestRate interestRate : interestRates) {
            if (interestRate.getCurrencyType().equals(currency)) {
                return interestRate;
            }
        }
        return null;
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

    public void displayFunds() {
        if (this.funds.size() != 0) {
            for (Fund fund : this.funds) {
                System.out.println(fund);
            }
        } else {
            System.out.println("Empty Funds");
        }

    }

    public void displayStocks() {
        if (this.stocks.size() != 0) {
            for (Stock stock : this.stocks) {
                System.out.println(stock);
            }
        } else {
            System.out.println("Empty Stocks");
        }
    }

    public boolean changeStockValue(int id, double newValue) {
        for (Stock stock : this.stocks) {
            if (id == stock.getId()) {
                stock.setPrice(newValue);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean changeFundValue(int id, double newValue) {
        for (Fund fund : this.funds) {
            if (id == fund.getId()) {
                fund.setPrice(newValue);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public boolean sellStock(Client client, int stockId, int accountId, int tryAccount) {
        Stock stock = getStockById(stockId);
        if (stock != null) {
            return client.buyStock(accountId, stock, tryAccount);
        }
        return false;
    }

    public boolean sellFund(Client client, int fundId, int accountId, int tryAccount) {
        Fund fund = getFundById(fundId);
        if (fund != null) {
            return client.buyFund(accountId, fund, tryAccount);
        }
        return false;
    }

    private Stock getStockById(int id) {
        for (Stock stock : stocks) {
            if (stock.getId() == id) {
                return stock;
            }
        }
        return null;
    }

    private Fund getFundById(int id) {
        for (Fund fund : funds) {
            if (fund.getId() == id) {
                return fund;
            }
        }
        return null;
    }

    public void passOneDay() {
        for (Client client : this.clients) {
            for (Account account : ((AccountGroup) client.getTopAccountGroup()).getAllAccounts()) {
                if (account instanceof CurrencyAccount && ((CurrencyAccount) account).isHasInterest()) {
                    CurrencyAccount currencyAccount = (CurrencyAccount) account;
                    InterestRate interestRate = findInterestRate(account.getType());
                    if (interestRate != null) {
                        currencyAccount.setValue(currencyAccount.getValue() + (currencyAccount.getValue() * interestRate.getInterestRate()));
                    }
                }
            }
        }
    }

    public double getPassedTimeValue(double value, int day, String currencyType) {
        InterestRate interestRate = findInterestRate(currencyType);
        if (interestRate != null) {
            for (int i = 0; i < day; i++) {
                value += (value * interestRate.getInterestRate());
            }
            return value;
        }
        return -1;
    }

}

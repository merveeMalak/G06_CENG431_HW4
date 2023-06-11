package account;

import bank.Fund;
import bank.Stock;
import simulation.TimeManagement;
import user.Bank;
import user.Client;

public class AccountManager {
    private Bank bank;
    private Client currentClient;
    private TimeManagement timeManagement;

    public AccountManager() {
        this.bank = new Bank();
        this.timeManagement = new TimeManagement();

    }

    public boolean createClient(String name) {
        if (!bank.checkClient(name)) {
            return false;
        }
        bank.addClient(new Client(name));
        return true;
    }

    public boolean loginClient(String name) {
        if (!bank.checkClient(name)) {
            currentClient = bank.getClient(name);
            return true;
        }
        return false;
    }

    public void displayClients() {
        bank.displayClients();
    }


    /**
     * For the exchange in between two account. If there is any error/invalid situation process, it will print the error/invalid situation and the exchange will not happen.
     *
     * @param firstAccountId  The account id of which account it will be taken from.
     * @param secondAccountId The account id of which account it will be transferred to.
     * @param value           The value of the exchanging process.
     */
    public void exchange(int firstAccountId, int secondAccountId, double value) {
        if (firstAccountId == secondAccountId) {
            System.out.println("Account ids must be different");
            return;
        }

        boolean isSecondInvalid = false;
        boolean isNotEnough = false;
        boolean isValidForExchange = false;
        boolean isNeedConverting = false;

        AccountComponent account1 = currentClient.getAccountComponentById(currentClient.getTopAccountGroup(), firstAccountId);
        AccountComponent account2 = currentClient.getAccountComponentById(currentClient.getTopAccountGroup(), secondAccountId);
        if (account1 == null || account2 == null || (account1 instanceof AccountGroup) || (account2 instanceof AccountGroup) || (account1 instanceof InvestmentAccount) || (account2 instanceof InvestmentAccount)) {
            System.out.println("Please check your inputs");
        } else if (this.timeManagement.checkAccountId(account1.getId()) || this.timeManagement.checkAccountId(account2.getId())) {
            System.out.println("Your interest account is locked, please try another day.");
        } else {
            CurrencyAccount castedAccount1 = (CurrencyAccount) account1;
            CurrencyAccount castedAccount2 = (CurrencyAccount) account2;

            // IF FIRST ACCOUNT IS WITH INTEREST
            if (castedAccount1.isHasInterest()) {
                String typeOfAccount1 = castedAccount1.getType();
                String typeOfAccount2 = castedAccount2.getType();

                if (typeOfAccount1.equals(typeOfAccount2)) {
                    if (!castedAccount2.isHasInterest()) {
                        // SAME CURRENCY TYPES
                        this.timeManagement.addLockedInterestAccount(castedAccount1.getId());
                        isValidForExchange = true;
                    } else {
                        isSecondInvalid = true;
                    }
                } else {
                    // DIFFERENT CURRENCY TYPES THIS IS INVALID SITUATION
                    isSecondInvalid = true;
                }

            } else {
                if (!castedAccount2.isHasInterest()) {
                    // IF FIRST ACCOUNT IS WITHOUT INTEREST SO WE NEED TO LOOK TYPES OF THESE ACCOUNTS
                    isValidForExchange = true;

                    // IF THEIR CURRENCIES NOT EQUAL WE NEED TO CONVERTING
                    isNeedConverting = !castedAccount1.getType().equals(castedAccount2.getType());
                } else {
                    String typeOfAccount1 = castedAccount1.getType();
                    String typeOfAccount2 = castedAccount2.getType();

                    if (typeOfAccount1.equals(typeOfAccount2)) {
                        this.timeManagement.addLockedInterestAccount(castedAccount2.getId());
                        // SAME CURRENCY TYPES
                        isValidForExchange = true;
                    } else {
                        // DIFFERENT CURRENCY TYPES THIS IS INVALID SITUATION
                        isSecondInvalid = true;
                    }
                }
            }

            if (isValidForExchange) {
                if (isNeedConverting) {
                    double convertedValue = this.bank.getCurrencyConvertedValue(castedAccount1.getType(), castedAccount2.getType(), value);
                    if (convertedValue != -1) {
                        if (castedAccount1.decreaseValue(value)) {
                            castedAccount2.increaseValue(convertedValue);
                        } else {
                            isNotEnough = true;
                        }
                    } else {
                        System.out.println("There is NO any currency rate with these currencies. Please contact your bank.");
                    }
                } else {
                    if (castedAccount1.decreaseValue(value)) {
                        castedAccount2.increaseValue(value);
                    } else {
                        isNotEnough = true;
                    }
                }
            }

            if (isSecondInvalid) {
                System.out.println("Please select correct receiver account type for exchange.");
            }
            if (isNotEnough) {
                System.out.println("Sender account has not enough value. Sender accounts value: " + castedAccount1.getValue());
            }
        }
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void displayCurrentClientAccounts() {
        currentClient.getAccountsInfo();
    }

    public boolean createAccount(AccountComponent newAccount, int parentId) {
        return currentClient.createAccount(newAccount, parentId);
    }

    public boolean checkIsAccountComponent(int id) {
        return (currentClient.getAccountComponentById(currentClient.getTopAccountGroup(), id) != null);
    }

    public boolean changeAccountGroup(int accountId, int newAccountId) {
        return currentClient.changeAccountGroup(accountId, newAccountId);
    }

    public boolean depositMoney(int id, double depositValue) {
        AccountComponent component = currentClient.getAccountComponentById(currentClient.getTopAccountGroup(), id);
        if (component instanceof CurrencyAccount && ((CurrencyAccount) component).getType().equals("TRY") && !((CurrencyAccount) component).isHasInterest()) {
            ((CurrencyAccount) component).increaseValue(depositValue);
            return true;
        }
        return false;
    }

    public void displayCurrencyRates() {
        bank.displayCurrencyRates();
    }

    public void createStock(String name, double value) {
        bank.createStock(name, value);
    }

    public void createFund(String name, double value) {
        bank.createFund(name, value);
    }

    public void displayStocks() {
        bank.displayStocks();
    }

    public void displayFunds() {
        bank.displayFunds();
    }

    public boolean changeValueStock(int id, double newValue) {
        return bank.changeStockValue(id, newValue);
    }

    public boolean changeValueFund(int id, double newValue) {
        return bank.changeFundValue(id, newValue);
    }

    public boolean setCurrencyRates(String currencyType1, String currencyType2, double value) {
        return bank.setCurrencyRate(currencyType1, currencyType2, value);
    }

    public boolean setInterestRates(String currency, double value) {
        return bank.setInterestRate(currency, value);
    }

    public boolean buyStock(int stockId, int accountId, int tryAccount) {
        return bank.sellStock(currentClient, stockId, accountId, tryAccount);
    }

    public boolean buyFund(int fundId, int accountId, int tryAccount) {
        return bank.sellFund(currentClient, fundId, accountId, tryAccount);
    }

    public boolean sellStock(int stockId, int accountId, int tryAccount) {
        Stock stock = currentClient.sellStock(accountId, stockId, tryAccount);
        return stock != null;
    }

    public boolean sellFund(int fundId, int accountId, int tryAccount) {
        Fund fund = currentClient.sellFund(accountId, fundId, tryAccount);
        return fund != null;
    }

    public void displayInterestRates() {
        bank.displayInterestRate();
    }

    public void passTime(int wantedDay) {
        for (int i = 0; i < wantedDay; i++) {
            this.bank.passOneDay();
            this.timeManagement.passOneDay();
        }
    }

    public int getCurrentDay() {
        return this.timeManagement.getCurrentDay();
    }

    public void getExpectedValue(int componentId, int passedDay) {
        double expectedValue = 0;
        String currencyType = "TRY";
        AccountComponent accountComponent = currentClient.getAccountComponentById(currentClient.getTopAccountGroup(), componentId);
        if (accountComponent instanceof Account) {
            if (accountComponent instanceof InvestmentAccount) {
                expectedValue = ((InvestmentAccount) accountComponent).getValue();
            } else {
                CurrencyAccount currencyAccount = (CurrencyAccount) accountComponent;
                if (((CurrencyAccount) accountComponent).isHasInterest()) {
                    expectedValue = this.bank.getPassedTimeValue(currencyAccount.getValue(), passedDay, currencyAccount.getType());
                } else {
                    expectedValue = ((CurrencyAccount) accountComponent).getValue();
                }
                currencyType = currencyAccount.getType();
            }
        } else {
            for (Account account : ((AccountGroup) accountComponent).getAllAccounts()) {
                if (account instanceof InvestmentAccount) {
                    expectedValue += account.getValue();
                } else {
                    if (((CurrencyAccount) account).isHasInterest()) {
                        CurrencyAccount currencyAccount = (CurrencyAccount) account;
                        double interestedValue = this.bank.getPassedTimeValue(currencyAccount.getValue(), passedDay, currencyType);
                        expectedValue += this.bank.getCurrencyConvertedValue(currencyAccount.getType(), currencyType, interestedValue);
                    } else {
                        expectedValue = account.getValue();
                    }
                }
            }
        }
        System.out.printf("Expected Value: %s %s\n", expectedValue, currencyType);
    }

    public double getBalanceOfAccountComponent(int componentId) {
        AccountComponent accountComponent = currentClient.getAccountComponentById(currentClient.getTopAccountGroup(), componentId);
        return this.bank.getComponentToConvertedValue(accountComponent, "TRY");
    }
}

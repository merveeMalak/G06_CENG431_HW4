package account;

import user.Bank;
import user.Client;

public class AccountManager {
    private Bank bank;
    private Client currentClient;

    public AccountManager() {
        this.bank = new Bank();

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
        } else {
            CurrencyAccount castedAccount1 = (CurrencyAccount) account1;
            CurrencyAccount castedAccount2 = (CurrencyAccount) account2;

            // IF FIRST ACCOUNT IS WITH INTEREST
            if (castedAccount1.isHasInterest()) {
                String typeOfAccount1 = castedAccount1.getType();
                String typeOfAccount2 = castedAccount2.getType();

                if (typeOfAccount1.equals(typeOfAccount2)) {
                    // SAME CURRENCY TYPES
                    isValidForExchange = true;
                } else {
                    // DIFFERENT CURRENCY TYPES THIS IS INVALID SITUATION
                    isSecondInvalid = true;
                }

            } else {
                // IF FIRST ACCOUNT IS WITHOUT INTEREST SO WE NEED TO LOOK TYPES OF THESE ACCOUNTS
                isValidForExchange = true;

                // IF THEIR CURRENCIES NOT EQUAL WE NEED TO CONVERTING
                isNeedConverting = castedAccount1.getType().equals(castedAccount2.getType());
            }

            if (isValidForExchange) {
                if (isNeedConverting) {
                    double exchangeRate = this.bank.getCurrencyConvertedValue(castedAccount1.getType(), castedAccount2.getType(), value);
                    if (exchangeRate != -1) {
                        if (castedAccount1.decreaseValue(value)) {
                            castedAccount2.increaseValue(value * exchangeRate);
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

    /**
     * To get TRY value from all accounts under a single account group.
     *
     * @return Total value in TRY founds in accounts, calculated on a cumulative basis.
     */
    public double getAllValueInTypeTRY(int id) {
        double valueInTRY = 0;
        AccountComponent accountComponent = currentClient.getAccountComponentById(currentClient.getTopAccountGroup(), id);

        if (accountComponent instanceof Account){
            if (accountComponent instanceof CurrencyAccount){

            }

        } else {



        }


        for (Account account : ((AccountGroup) currentClient.getTopAccountGroup()).getAllAccounts()) {
            if (account instanceof CurrencyAccount) {

            } else {
                // TODO INVESTMENT
            }
        }
        return valueInTRY;
    }
}

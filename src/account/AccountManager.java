package account;

import user.Bank;
import user.Client;

public class AccountManager {
    private Bank bank;
    private Client currentClient;
    public AccountManager(){
        this.bank = new Bank();

    }
    public boolean createClient(String name){
        if (!bank.checkClient(name)){
            return false;
        }
        bank.addClient(new Client(name));
        return true;
    }
    public boolean loginClient(String name){
        if (!bank.checkClient(name)){
            currentClient = bank.getClient(name);
            return true;
        }
        return false;
    }

    public void displayClients() {
        bank.displayClients();
    }

    public void exchange(int firstAccountId, int secondAccountId, double value) {
        if (firstAccountId == secondAccountId) {
            System.out.println("Account ids must be different");
            return;
        }

        boolean isSecondInvalid = false;
        boolean isNotEnough = false;

        AccountComponent account1 = currentClient.getAccountComponentById(currentClient.getTopAccountGroup(), firstAccountId);
        AccountComponent account2 = currentClient.getAccountComponentById(currentClient.getTopAccountGroup(), secondAccountId);
        if (account1 == null || account2 == null || (account1 instanceof AccountGroup) || (account2 instanceof AccountGroup)) {
            System.out.println("Please check your inputs");
        } else {
            Account castedAccount1 = (Account) account1;
            Account castedAccount2 = (Account) account2;
            switch (castedAccount1.getType()) {
                case "EURWithoutInterest" -> {
                    EURWithoutInterest eurWithoutInterest1 = (EURWithoutInterest) castedAccount1;
                    switch (castedAccount2.getType()) {
                        case "EURWithoutInterest" -> {
                            EURWithoutInterest eurWithoutInterest2 = (EURWithoutInterest) castedAccount2;
                            if (eurWithoutInterest1.decreaseValue(value)) {
                                eurWithoutInterest2.increaseValue(value);
                            } else {
                                isNotEnough = true;
                            }
                        }
                        case "TRYWithoutInterest" -> {
                            TRYWithoutInterest tryWithoutInterest2 = (TRYWithoutInterest) castedAccount2;
                            if (eurWithoutInterest1.decreaseValue(value)) {
                                tryWithoutInterest2.increaseValue(value / bank.getCurrentRateTRYtoEUR());
                            } else {
                                isNotEnough = true;
                            }
                        }
                        default -> {
                            isSecondInvalid = true;
                        }
                    }
                }
                case "TRYWithoutInterest" -> {
                    TRYWithoutInterest tryWithoutInterest1 = (TRYWithoutInterest) castedAccount1;
                    switch (castedAccount2.getType()) {
                        case "EURWithoutInterest" -> {
                            EURWithoutInterest eurWithoutInterest2 = (EURWithoutInterest) castedAccount2;
                            if (tryWithoutInterest1.decreaseValue(value)) {
                                eurWithoutInterest2.increaseValue(value * bank.getCurrentRateTRYtoEUR());
                            } else {
                                isNotEnough = true;
                            }
                        }
                        case "TRYWithoutInterest" -> {
                            TRYWithoutInterest tryWithoutInterest2 = (TRYWithoutInterest) castedAccount2;
                            if (tryWithoutInterest1.decreaseValue(value)) {
                                tryWithoutInterest2.increaseValue(value);
                            } else {
                                isNotEnough = true;
                            }
                        }
                        case "XAUWithoutInterest" -> {
                            XAUWithoutInterest xauWithoutInterest2 = (XAUWithoutInterest) castedAccount2;
                            if (tryWithoutInterest1.decreaseValue(value)) {
                                xauWithoutInterest2.increaseValue(value * bank.getCurrentRateTRYtoXAU());
                            } else {
                                isNotEnough = true;
                            }
                        }
                        case "USDWithoutInterest" -> {
                            USDWithoutInterest usdWithoutInterest2 = (USDWithoutInterest) castedAccount2;
                            if (tryWithoutInterest1.decreaseValue(value)) {
                                usdWithoutInterest2.increaseValue(value * bank.getCurrentRateTRYtoUSD());
                            } else {
                                isNotEnough = true;
                            }
                        }
                        default -> {
                            isSecondInvalid = true;
                        }
                    }
                }
                case "USDWithoutInterest" -> {
                    USDWithoutInterest usdWithoutInterest1 = (USDWithoutInterest) castedAccount1;
                    switch (castedAccount2.getType()) {
                        case "USDWithoutInterest" -> {
                            USDWithoutInterest usdWithoutInterest2 = (USDWithoutInterest) castedAccount2;
                            if (usdWithoutInterest1.decreaseValue(value)) {
                                usdWithoutInterest2.increaseValue(value);
                            } else {
                                isNotEnough = true;
                            }
                        }
                        case "TRYWithoutInterest" -> {
                            TRYWithoutInterest tryWithoutInterest2 = (TRYWithoutInterest) castedAccount2;
                            if (usdWithoutInterest1.decreaseValue(value)) {
                                tryWithoutInterest2.increaseValue(value / bank.getCurrentRateTRYtoUSD());
                            } else {
                                isNotEnough = true;
                            }
                        }
                        default -> {
                            isSecondInvalid = true;
                        }
                    }
                }
                case "XAUWithoutInterest" -> {
                    XAUWithoutInterest xauWithoutInterest1 = (XAUWithoutInterest) castedAccount1;
                    switch (castedAccount2.getType()) {
                        case "XAUWithoutInterest" -> {
                            XAUWithoutInterest xauWithoutInterest2 = (XAUWithoutInterest) castedAccount2;
                            if (xauWithoutInterest1.decreaseValue(value)) {
                                xauWithoutInterest2.increaseValue(value);
                            } else {
                                isNotEnough = true;
                            }
                        }
                        case "TRYWithoutInterest" -> {
                            TRYWithoutInterest tryWithoutInterest2 = (TRYWithoutInterest) castedAccount2;
                            if (xauWithoutInterest1.decreaseValue(value)) {
                                tryWithoutInterest2.increaseValue(value / bank.getCurrentRateTRYtoXAU());
                            } else {
                                isNotEnough = true;
                            }
                        }
                        default -> {
                            isSecondInvalid = true;
                        }
                    }
                }

                // WITH INTEREST SIDE

                case "EURWithInterest" -> {
                    EURWithInterest eurWithInterest1 = (EURWithInterest) castedAccount1;
                    if (castedAccount2.getType().equals("EURWithoutInterest")) {
                        EURWithoutInterest eurWithoutInterest2 = (EURWithoutInterest) castedAccount2;
                        if (eurWithInterest1.decreaseValue(value)) {
                            eurWithoutInterest2.increaseValue(value);
                        } else {
                            isNotEnough = true;
                        }
                    } else {
                        isSecondInvalid = true;
                    }
                }
                case "USDWithInterest" -> {
                    USDWithInterest usdWithInterest1 = (USDWithInterest) castedAccount1;
                    if (castedAccount2.getType().equals("USDWithoutInterest")) {
                        USDWithoutInterest usdWithoutInterest2 = (USDWithoutInterest) castedAccount2;
                        if (usdWithInterest1.decreaseValue(value)) {
                            usdWithoutInterest2.increaseValue(value);
                        } else {
                            isNotEnough = true;
                        }
                    } else {
                        isSecondInvalid = true;
                    }
                }
                case "TRYWithInterest" -> {
                    TRYWithInterest tryWithInterest1 = (TRYWithInterest) castedAccount1;
                    if (castedAccount2.getType().equals("TRYWithoutInterest")) {
                        TRYWithoutInterest tryWithoutInterest2 = (TRYWithoutInterest) castedAccount2;
                        if (tryWithInterest1.decreaseValue(value)) {
                            tryWithoutInterest2.increaseValue(value);
                        } else {
                            isNotEnough = true;
                        }
                    } else {
                        isSecondInvalid = true;
                    }
                }
                case "XAUWithInterest" -> {
                    XAUWithInterest xauWithInterest1 = (XAUWithInterest) castedAccount1;
                    if (castedAccount2.getType().equals("XAUWithoutInterest")) {
                        XAUWithoutInterest xauWithoutInterest = (XAUWithoutInterest) castedAccount2;
                        if (xauWithInterest1.decreaseValue(value)) {
                            xauWithoutInterest.increaseValue(value);
                        } else {
                            isNotEnough = true;
                        }
                    } else {
                        isSecondInvalid = true;
                    }
                }
            }

            if (isSecondInvalid) {
                System.out.println("Please select correct receiver account type for exchange.");
            }
            if (isNotEnough) {
                System.out.println("Sender account has not enough value.");
            }
        }
    }

    public Client getCurrentClient() {
        return currentClient;
    }

    public void displayCurrentClientAccounts(){
        currentClient.getAccountsInfo();
    }
    public boolean createAccount(AccountComponent newAccount, int parentId){
        return currentClient.createAccount(newAccount, parentId);
    }
    public boolean checkIsAccountComponent(int id){
        return (currentClient.getAccountComponentById(currentClient.getTopAccountGroup(), id) != null);
    }
    public boolean changeAccountGroup(int accountId, int newAccountId){
        return currentClient.changeAccountGroup(accountId,newAccountId);
    }

}

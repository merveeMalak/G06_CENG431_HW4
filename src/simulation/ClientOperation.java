package simulation;

import account.AccountComponent;
import account.AccountGroup;
import account.AccountManager;
import accountFactory.AccountFactory;
import accountFactory.CurrencyAccountFactory;
import accountFactory.InvestmentAccountFactory;

import java.util.Scanner;

public class ClientOperation {

    private Scanner sc;
    private AccountManager accountManager;
    private int currentId;
    private boolean isLogout;
    public ClientOperation(AccountManager accountManager){
        this.sc = new Scanner(System.in);
        this.accountManager = accountManager;
        currentId = 3;
        this.isLogout = false;
    }
    public void operationsForClient() {
        int choice;
        while (!isLogout) {
            System.out.print("""
                    0-logout
                    1-display my accounts
                    2-create account
                    3-create account group
                    4-change account group of account
                    5-deposit Money
                    6-exchange money between accounts
                    7-display currency rates
                    8-buy stock
                    9-buy fund
                    10-sell stock
                    11-sell fund
                    12-display interest rates
                    Select an operation you want to do :\s""");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 0 -> isLogout = true;
                case 1 -> displayCurrentClientAccounts();
                case 2 -> createAccount();
                case 3 -> createAccountGroup();
                case 4 -> changeAccountGroupOfAccountComponent();
                case 5 -> depositMoney();
                case 6 -> exchange();
                case 7 -> displayCurrencyRates();
                case 8 -> buyStock();
                case 9 -> buyFund();
                case 10 -> sellStock();
                case 11 -> sellFunds();
                case 12 -> displayInterestRates();
                default -> System.out.println("Invalid input");
            }

        }
    }




    private void displayCurrentClientAccounts() {
        System.out.println("........Your accounts.......");
        accountManager.displayCurrentClientAccounts();
    }

    private void createAccount() {
        displayAccountTypes();
        System.out.print("Select account type (0 ->main menu): ");
        boolean isFinished = false;
        int accountType;
        while (!isFinished) {
            accountType = sc.nextInt();
            sc.nextLine();
            if (accountType == 0) {
                isFinished = true;
            } else if (accountType > 9) {
                System.out.println("Invalid account type");
            } else {
                displayCurrentClientAccounts();
                System.out.print("Select an account group to add new account (0-> main menu): ");
                int accountGroup = sc.nextInt();
                sc.nextLine();
                if (accountGroup != 0) {
                    AccountComponent newAccount = createAccountFromFactory(accountType);
                    if (accountManager.createAccount(newAccount, accountGroup)) {
                        System.out.println("New Account added into account group " + accountGroup);
                        displayCurrentClientAccounts();
                        currentId++;
                    } else {
                        System.out.println("Please check your input. Process is unsuccessful");
                    }
                }
                isFinished = true;
            }
        }


    }

    private AccountComponent createAccountFromFactory(int id){
        AccountFactory accountFactory;
        if (id != 9) {
            accountFactory = new CurrencyAccountFactory();
        }
        else {
            accountFactory = new InvestmentAccountFactory();
        }
        String currencyType = (id == 1 || id == 2) ? "TRY" : (id == 3 || id == 4) ? "EUR" : (id == 5 || id == 6) ? "USD" : "XAU";
        return accountFactory.createAccount(currentId,currencyType, id % 2 == 0);

    }

    private void displayAccountTypes() {
        System.out.println("""
                ....ACCOUNT TYPES....
                1-Regular Account (TRY) without Interest
                2-Regular Account (TRY) with Interest
                3-Foreign Currency Account (EUR) without Interest
                4-Foreign Currency Account (EUR) with Interest
                5-Foreign Currency Account (USD) without Interest
                6-Foreign Currency Account (USD) with Interest
                7-Gold Account (XAU) without Interest
                8-Gold Account (XAU) with Interest
                9-Investment Account""");
    }

    private void createAccountGroup() {
        System.out.print("Input New account name (q -> main menu) :");
        String groupName = sc.nextLine();
        if (groupName.equalsIgnoreCase("q")) {
            return;
        }
        displayCurrentClientAccounts();
        System.out.print("Select an account group (0-> main menu): ");
        int groupId = sc.nextInt();
        sc.nextLine();
        if (groupId == 0) {
            return;
        }
        AccountComponent accountGroup = new AccountGroup(currentId, groupName);
        if (accountManager.createAccount(accountGroup, groupId)) {
            System.out.println("New Account Group added into account group " + groupId);
            displayCurrentClientAccounts();
            currentId++;
        } else {
            System.out.println("Please check your input. Process is unsuccessful");
        }
    }

    private void changeAccountGroupOfAccountComponent() {
        displayCurrentClientAccounts();
        System.out.print("Enter the account for which you want to change the account group or the id of the account group (0-> main menu) : ");
        int accountId = sc.nextInt();
        sc.nextLine();
        if (accountId == 0) {
            return;
        }
        if (accountId == 1){
            System.out.println("Top account group is not changed!");
            return;
        }
        if (!accountManager.checkIsAccountComponent(accountId)){
            System.out.println("There is no account or account group with entered id");
            return;
        }
        System.out.print("Enter the account group id to move (0-> main menu) :");
        int accountGroup = sc.nextInt();
        sc.nextLine();
        if (accountGroup == 0) {
            return;
        }
        if (accountManager.changeAccountGroup(accountId, accountGroup)) {
            System.out.println("Account moved to account group'" + accountGroup);
            displayCurrentClientAccounts();
        } else {
            System.out.println("Please check your input. Process is unsuccessful");
        }

    }
    private void depositMoney(){
        displayCurrentClientAccounts();
        System.out.print("Select account (0->main menu): ");
        int firstAccount = sc.nextInt();
        sc.nextLine();
        if (firstAccount == 0) {
            return;
        }
        System.out.print("Enter deposit value (0->main menu): ");
        double exchangeValue = sc.nextDouble();
        sc.nextLine();
        if (exchangeValue == 0) {
            return;
        }
        if (accountManager.depositMoney(firstAccount, exchangeValue)){
            System.out.println("Deposit money operation is success");
            displayCurrentClientAccounts();
        }
        else{
            System.out.println("Operation is failed!");
        }
    }
    private void exchange(){
        displayCurrentClientAccounts();
        System.out.print("Select sender account (0->main menu): ");
        int firstAccount = sc.nextInt();
        sc.nextLine();
        if (firstAccount == 0) {
            return;
        }
        System.out.print("Select receiver account (0->main menu): ");
        int secondAccount = sc.nextInt();
        sc.nextLine();
        if (secondAccount == 0) {
            return;
        }
        System.out.print("Enter exchange value (0->main menu): ");
        double exchangeValue = sc.nextDouble();
        sc.nextLine();
        if (exchangeValue == 0) {
            return;
        }
        accountManager.exchange(firstAccount,secondAccount,exchangeValue);
        displayCurrentClientAccounts();
    }
    private void displayCurrencyRates(){
        accountManager.displayCurrencyRates();
    }

    public void setLogout(boolean logout){
        this.isLogout = logout;
    }

    private void buyStock(){
        displayCurrentClientAccounts();
        System.out.print("Select (TRY) Regular Without Interest Account to buy a stock (0->main menu): ");
        int tryAccount = sc.nextInt();
        sc.nextLine();
        if (tryAccount == 0) {
            return;
        }
        System.out.print("Select investment account to buy stock (0->main menu): ");
        int account = sc.nextInt();
        sc.nextLine();
        if (account == 0) {
            return;
        }
        accountManager.displayStocks();
        System.out.print("Select stock (0->main menu): ");
        int stock = sc.nextInt();
        sc.nextLine();
        if (stock == 0) {
            return;
        }
        if (accountManager.buyStock(stock,account, tryAccount)){
            System.out.println("Stock is bought successfully");
            displayCurrentClientAccounts();
            return;
        }
        System.out.println("Stock is bought failed");

    }

    private void buyFund(){
        displayCurrentClientAccounts();
        System.out.print("Select (TRY) Regular Without Interest Account to buy a fund (0->main menu): ");
        int tryAccount = sc.nextInt();
        sc.nextLine();
        if (tryAccount == 0) {
            return;
        }
        System.out.print("Select investment account to buy fund (0->main menu): ");
        int account = sc.nextInt();
        sc.nextLine();
        if (account == 0) {
            return;
        }
        accountManager.displayFunds();
        System.out.print("Select fund (0->main menu): ");
        int fund = sc.nextInt();
        sc.nextLine();
        if (fund == 0) {
            return;
        }
        if (accountManager.buyFund(fund,account,tryAccount)){
            System.out.println("Funds is bought successfully");
            displayCurrentClientAccounts();
            return;
        }
        System.out.println("Fund is bought failed");
    }

    private void sellStock(){
        displayCurrentClientAccounts();
        System.out.print("Select (TRY) Regular Without Interest Account to sell a stock (0->main menu): ");
        int tryAccount = sc.nextInt();
        sc.nextLine();
        if (tryAccount == 0) {
            return;
        }
        System.out.print("Select investment account to sell stock (0->main menu): ");
        int account = sc.nextInt();
        sc.nextLine();
        if (account == 0) {
            return;
        }
        System.out.print("Select stock (0->main menu): ");
        int stock = sc.nextInt();
        sc.nextLine();
        if (stock == 0) {
            return;
        }
        if (accountManager.sellStock(stock,account,tryAccount)){
            System.out.println("Stock is sold successfully");
            displayCurrentClientAccounts();
            return;
        }
        System.out.println("Stock is sold failed");
    }
    private void sellFunds(){
        displayCurrentClientAccounts();
        System.out.print("Select (TRY) Regular Without Interest Account to sell a fund (0->main menu): ");
        int tryAccount = sc.nextInt();
        sc.nextLine();
        if (tryAccount == 0) {
            return;
        }
        System.out.print("Select investment account to sell fund (0->main menu): ");
        int account = sc.nextInt();
        sc.nextLine();
        if (account == 0) {
            return;
        }
        System.out.print("Select fund (0->main menu): ");
        int fund = sc.nextInt();
        sc.nextLine();
        if (fund == 0) {
            return;
        }
        if (accountManager.sellFund(fund,account, tryAccount)){
            System.out.println("Fund is sold successfully");
            displayCurrentClientAccounts();
            return;
        }
        System.out.println("Fund is sold failed");
    }

    private void displayInterestRates() {
        accountManager.displayInterestRates();
    }
}

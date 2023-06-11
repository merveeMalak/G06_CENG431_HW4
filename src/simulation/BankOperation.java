package simulation;

import account.AccountManager;

import java.util.Scanner;

public class BankOperation {
    private AccountManager accountManager;
    private Scanner sc;
    public BankOperation(AccountManager accountManager){
        this.accountManager = accountManager;
        sc  = new Scanner(System.in);
    }

    public void operationForBank(){
        int choice;
        do{
            System.out.print("""
                    0-main menu
                    1-display Stocks
                    2-create Stock
                    3-display Funds
                    4-create Fund
                    5-change Value Stock
                    6-change Value Fund
                    7-change Currency Rates
                    8-change Interest Rates
                    9-pass time
                    Select an operation you want to do :\s""");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 0 -> System.out.println("Returns main menu...");
                case 1 -> displayStocks();
                case 2 -> createStock();
                case 3 -> displayFunds();
                case 4 -> createFund();
                case 5 -> changeValueStock();
                case 6 -> changeValueFund();
                case 7 -> changeCurrencyRates();
                case 8 -> changeInterestRates();
                case 9 -> passTime();
                default -> System.out.println("Invalid input");
            }

        }while (choice != 0);
    }


    private void displayStocks(){
        accountManager.displayStocks();
    }
    private void displayFunds(){
        accountManager.displayFunds();
    }
    private void createStock() {
        System.out.print("Enter stock name (q->main menu): ");
        String name = sc.nextLine();
        if (name.equalsIgnoreCase("q")) {
            return;
        }
        System.out.print("Enter stock value (0->main menu): ");
        double value = sc.nextDouble();
        sc.nextLine();
        if (value == 0) {
            return;
        }
        accountManager.createStock(name,value);
        accountManager.displayStocks();
    }
    private void createFund() {
        System.out.print("Enter fund name (q->main menu): ");
        String name = sc.nextLine();
        if (name.equalsIgnoreCase("q")) {
            return;
        }
        System.out.print("Enter fund value (0->main menu): ");
        double value = sc.nextDouble();
        sc.nextLine();
        if (value == 0) {
            return;
        }
        accountManager.createFund(name,value);
        accountManager.displayFunds();
    }

    private void changeValueStock() {
        accountManager.displayStocks();
        System.out.print("Select id (0->main menu): ");
        int id = sc.nextInt();
        sc.nextLine();
        if (id == 0) {
            return;
        }
        System.out.print("Enter new value (0->main menu): ");
        double newValue = sc.nextDouble();
        sc.nextLine();
        if (newValue == 0) {
            return;
        }
        if (accountManager.changeValueStock(id, newValue)){
            System.out.println("Stock value changed successfully");
            accountManager.displayStocks();
        }
        else{
            System.out.println("Stock value changed failed");
        }
    }
    private void changeValueFund() {
        accountManager.displayFunds();
        System.out.print("Select id (0->main menu): ");
        int id = sc.nextInt();
        sc.nextLine();
        if (id == 0) {
            return;
        }
        System.out.print("Enter new value (0->main menu): ");
        double newValue = sc.nextDouble();
        sc.nextLine();
        if (newValue == 0) {
            return;
        }
        if (accountManager.changeValueFund(id, newValue)){
            System.out.println("Fund value changed successfully");
            accountManager.displayFunds();
        }
        else{
            System.out.println("Fund value changed failed");
        }
    }

    private void changeCurrencyRates() {
        accountManager.displayCurrencyRates();
        System.out.print("Enter currency 1 (q->main menu): ");
        String firstCurrency = sc.nextLine();
        if (firstCurrency.equalsIgnoreCase("q")) {
            return;
        }
        System.out.print("Enter currency 2 (q->main menu): ");
        String secondCurrency = sc.nextLine();
        if (secondCurrency.equalsIgnoreCase("q")) {
            return;
        }
        System.out.print("Enter new currency rate (0->main menu): ");
        double newCurrencyRate = sc.nextDouble();
        sc.nextLine();
        if (newCurrencyRate == 0) {
            return;
        }
        if (accountManager.setCurrencyRates(firstCurrency,secondCurrency,newCurrencyRate)){
            System.out.println("Currency rates are changed successfully");
            accountManager.displayCurrencyRates();
            return;
        }
        System.out.println("Currency rates are changed failed");

    }
    private void changeInterestRates() {
        accountManager.displayInterestRates();
        System.out.print("Enter currency type (q->main menu): ");
        String currency = sc.nextLine();
        if (currency.equalsIgnoreCase("q")) {
            return;
        }

        System.out.print("Enter new interest rate (0->main menu): ");
        double newInterestRate = sc.nextDouble();
        sc.nextLine();
        if (newInterestRate == 0) {
            return;
        }
        if (accountManager.setInterestRates(currency, newInterestRate)){
            System.out.println("Currency rates are changed successfully");
            accountManager.displayInterestRates();
            return;
        }
        System.out.println("Currency rates are changed failed");
    }


    private void passTime() {
    }




}

import account.AccountComponent;
import account.AccountGroup;
import account.AccountManager;
import accountFactory.*;

import java.util.Scanner;

public class BankingSimulation {
    private AccountManager accountManager;
    private boolean isExit;
    private boolean isLogout;
    private Scanner sc;
    private int currentId;
    public BankingSimulation(){
        this.accountManager = new AccountManager();
        this.isExit = false;
        this.isLogout = false;
        sc = new Scanner(System.in);
        currentId = 3;
    }
    public void startSimulation(){
        System.out.println("Welcome Banking Application");

        int input;
        while(!isExit){
            System.out.print("select option (0-exit, 1-create client, 2-login client): ");
            input = sc.nextInt();
            sc.nextLine();
            isLogout = false;
            switch (input){
                case 0 -> isExit = true;

                case 1 -> createClient();

                case 2 -> {
                    displayClient();
                    loginClient();

                }default -> System.out.println("Invalid option");

        }
    }
    }
    private void createClient(){
        System.out.print("Input client name: ");
        String name = sc.nextLine();
        accountManager.createClient(name);
    }
    private void displayClient(){
        System.out.println(".......CLIENTS.......");
        accountManager.displayClients();
    }
    private void loginClient(){
        System.out.print("Select client (input client name, q-> main menu): ");
        String name = sc.nextLine();
        if (name.equalsIgnoreCase("q")){}
        else if (accountManager.loginClient(name)){
            System.out.println("********** Welcome "+ name + " **********");
            operationsForClient();
        }else {
            System.out.println("Invalid name!");
        }
    }
    private void operationsForClient(){
        int choice;
        while(!isLogout){
            System.out.print("0-logout\n1-display my accounts\n2-create account\n3-create account group\n4-change account group of account" +
                    "\nSelect an operation you want to do : ");
            choice= sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 0 -> isLogout = true;
                case 1 -> displayCurrentClientAccounts();
                case 2 -> createAccount();
                case 3 -> createAccountGroup();
                case 4 -> changeAccountGroupOfAccountComponent();
            }

        }
    }


    private void displayCurrentClientAccounts(){
        System.out.println("........Your accounts.......");
        accountManager.displayCurrentClientAccounts();
    }

    private void createAccount(){
        displayAccountTypes();
        System.out.print("Select account type (0 ->main menu): ");
        boolean isFinished = false;
        int accountType;
        while (!isFinished){
            accountType = sc.nextInt();
            sc.nextLine();
            if (accountType == 0){
                isFinished = true;
            }else if (accountType > 9){
                System.out.println("Invalid account type");
            }else{
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
        if (id ==1 || id == 2){
            accountFactory = new TRYAccountFactory();
        }else if (id == 3 || id == 4) {
            accountFactory = new EURAccountFactory();
        } else if (id == 5 || id == 6){
            accountFactory = new USDAccountFactory();
        }else if (id == 7 || id == 8){
            accountFactory = new XAUAccountFactory();
        }else {
            accountFactory = new InvestmentAccountFactory();
        }
        return accountFactory.createAccount(currentId, id % 2 == 0);

    }

    private void displayAccountTypes(){
        System.out.println("....ACCOUNT TYPES....\n1-Regular Account (TRY) without Interest\n2-Regular Account (TRY) with Interest\n" +
                "3-Foreign Currency Account (EUR) without Interest\n4-Foreign Currency Account (EUR) with Interest\n" +
                "5-Foreign Currency Account (USD) without Interest\n6-Foreign Currency Account (USD) with Interest\n" +
                "7-Gold Account (XAU) without Interest\n8-Gold Account (XAU) with Interest\n9-Investment Account");
    }
    private void createAccountGroup(){
        System.out.print("Input New account name (q -> main menu) :");
        String groupName = sc.nextLine();
        if (groupName.equalsIgnoreCase("q")){
            return;
        }
        displayCurrentClientAccounts();
        System.out.print("Select an account group (0-> main menu): ");
        int groupId = sc.nextInt();
        sc.nextLine();
        if (groupId == 0){
            return;
        }
        AccountComponent accountGroup = new AccountGroup(currentId, groupName);
        if (accountManager.createAccount(accountGroup, groupId)){
            System.out.println("New Account Group added into account group " + groupId);
            displayCurrentClientAccounts();
            currentId++;
        }else{
            System.out.println("Please check your input. Process is unsuccessful");
        }
    }

    private void changeAccountGroupOfAccountComponent() {
        displayCurrentClientAccounts();
        System.out.print("Enter the account for which you want to change the account group or the id of the account group (0-> main menu) : ");
        int accountId = sc.nextInt();
        sc.nextLine();
        if (accountId == 0){
            return;
        }
        if (!accountManager.checkIsAccountComponent(accountId)){
            System.out.println("There is no account or account group with entered id");
            return;
        }
        System.out.print("Enter the account group id to move (0-> main menu) :");
        int accountGroup = sc.nextInt();
        sc.nextLine();
        if (accountGroup == 0){
            return;
        }
        if (accountManager.changeAccountGroup(accountId,accountGroup)){
            System.out.println("Account moved to account group'" + accountGroup);
            displayCurrentClientAccounts();
        }else{
            System.out.println("Please check your input. Process is unsuccessful");
        }

    }



}

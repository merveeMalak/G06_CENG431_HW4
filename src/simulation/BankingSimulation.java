package simulation;

import account.AccountManager;
import account.AccountComponent;
import account.AccountGroup;
import accountFactory.*;

import java.util.Scanner;

public class BankingSimulation {
    private AccountManager accountManager;
    private boolean isExit;
    private ClientOperation clientOperation;
    private BankOperation bankOperation;

    private Scanner sc;


    public BankingSimulation() {
        this.accountManager = new AccountManager();
        this.isExit = false;
        sc = new Scanner(System.in);
        this.clientOperation = new ClientOperation(accountManager);
        this.bankOperation = new BankOperation(accountManager);

    }

    public void startSimulation() {
        System.out.println("Welcome Banking Application");

        int input;
        while (!isExit) {
            System.out.print("select option (0-exit, 1-create client, 2-login client 3-bank operation): ");
            input = sc.nextInt();
            sc.nextLine();
            clientOperation.setLogout(false);
            switch (input) {
                case 0 -> isExit = true;

                case 1 -> createClient();

                case 2 -> {
                    displayClient();
                    loginClient();

                }
                case 3-> bankOperation.operationForBank();
                default -> System.out.println("Invalid option");

            }
        }
    }

    private void createClient() {
        System.out.print("Input client name: ");
        String name = sc.nextLine();
        accountManager.createClient(name);
    }

    private void displayClient() {
        System.out.println(".......CLIENTS.......");
        accountManager.displayClients();
    }

    private void loginClient() {
        System.out.print("Select client (input client name, q-> main menu): ");
        String name = sc.nextLine();
        if (name.equalsIgnoreCase("q")) {
        } else if (accountManager.loginClient(name)) {
            System.out.println("********** Welcome " + name + " **********");
            clientOperation.operationsForClient();
        } else {
            System.out.println("Invalid name!");
        }
    }






}

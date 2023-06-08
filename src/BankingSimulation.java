import account.AccountManager;

import java.util.Scanner;

public class BankingSimulation {
    private AccountManager accountManager;
    private boolean isExit;
    private boolean isLogout;

    public BankingSimulation() {
        this.accountManager = new AccountManager();
        this.isExit = false;
        this.isLogout = false;
    }

    public void startSimulation() {
        System.out.println("Welcome Banking Application");
        Scanner sc = new Scanner(System.in);
        char input;
        while (!isExit) {
            System.out.println("Please Enter An Option (0-Exit / 1-Create Client / 2-Login Client)");
            input = sc.next().charAt(0);
            switch (input) {
                case 0 -> {
                    System.out.println("Good Bye!");
                    isExit = true;
                }
                case 1 -> {
                    System.out.println("Please enter name:");
                    String name = sc.next();
                    if (accountManager.createClient(name)) {
                        System.out.println("Successfully created...");
                    } else {
                        System.out.println("Please choice another name...");
                    }
                }
                case 2 -> {
                    System.out.println(".......CLIENTS.......");
                    accountManager.displayClients();
                    String name = sc.next();
                    if (accountManager.loginClient(name)) {
                        System.out.println("Login Successful!");
                        while (!isLogout) {

                        }
                    } else {
                        System.out.println("Invalid name!");
                    }
                }
            }
        }
    }
}

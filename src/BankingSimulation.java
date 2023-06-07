import account.AccountManager;

import java.util.Scanner;

public class BankingSimulation {
    private AccountManager accountManager;
    private boolean isExit;
    private boolean isLogout;
    public BankingSimulation(){
        this.accountManager = new AccountManager();
        this.isExit = false;
        this.isLogout = false;
    }
    public void startSimulation(){
        System.out.println("Welcome Banking Application");
        Scanner sc = new Scanner (System.in);
        char input;
        while(!isExit){
            System.out.println("select option (0-exit, 1-create client, 2-login client)");

            input = sc.next().charAt(0);
            switch (input){
                case 0 -> {
                    isExit = true;
                }
                case 1 -> {
                    String name = sc.next();
                    accountManager.createClient(name);
                }
                case 2 ->{
                    System.out.println(".......CLIENTS.......");
                    accountManager.displayClients();
                    String name = sc.next();
                    if (accountManager.loginClient(name)){
                        System.out.println("Login Successful!");
                        while(!isLogout){

                        }
                    }else {
                        System.out.println("Invalid name!");
                    }
                }
        }
    }
    }
}

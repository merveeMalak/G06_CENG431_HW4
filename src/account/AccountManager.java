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

    public void displayClients(){
        bank.displayClients();
    }
    public void exchange(int firstAccountId, int secondAccountId, double value){
        if (firstAccountId == secondAccountId){
            System.out.println("Account id's must be different");
            return;
        }
        AccountComponent account1 = currentClient.getAccountComponentById(currentClient.getTopAccountGroup(), firstAccountId);
        AccountComponent account2 = currentClient.getAccountComponentById(currentClient.getTopAccountGroup(), secondAccountId);
        if (account1 == null || account2 == null || (account1 instanceof AccountGroup) || (account2 instanceof AccountGroup) ){
            System.out.println("Please check again inputs");
        }else{
            
        }

    }

}

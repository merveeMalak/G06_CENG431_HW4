package account;

import java.util.ArrayList;
import java.util.List;

public class AccountGroup extends AccountComponent{

    private String name;
    private List<AccountComponent> accounts;



    public  AccountGroup(int id, String name){
        super(id);
        this.name = name;
        accounts = new ArrayList<>();

    }
    @Override
    public void getBalance() {

    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    public void addAccount(AccountComponent account){
        accounts.add(account);
    }

    public void removeAccount(AccountComponent account){
        accounts.remove(account);
    }


    public List<AccountComponent> getAccountGroup(){
        return this.accounts;
    }
    public int sizeAccountGroup(){
        return this.accounts.size();
    }

    @Override
    public String toString() {
        String accountsInfo =  "Account Group " + name + ":\n";
        for (AccountComponent account: accounts){
            accountsInfo += "    " + account.toString() + " \n";
        }
        return accountsInfo;
    }
}

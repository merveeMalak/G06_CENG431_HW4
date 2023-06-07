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

    @Override
    public void getAccountInfo() {
        System.out.println("id: " + id + " - Account Group: "  +name );

    }

    public void addAccount(AccountComponent account){
        accounts.add(account);
    }

    public void removeAccount(AccountComponent account){
        accounts.remove(account);
    }


    public List<AccountComponent> getAccounts(){
        return this.accounts;
    }
    public int sizeAccountGroup(){
        return this.accounts.size();
    }

    public String getName(){
        return name;
    }


}

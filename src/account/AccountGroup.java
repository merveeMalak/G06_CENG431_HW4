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
    public double getValue() {
        return 0;
    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    @Override
    public void getAccountInfo() {
        System.out.println("Id: " + id + " - Account Group: "  +name );

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

    public List<Account> getAllAccounts() {
        List<Account> accounts = new ArrayList<>();
        for (AccountComponent component : this.getAccounts()) {
            if (component instanceof Account) {
                accounts.add((Account) component);
            } else if (component instanceof AccountGroup) {
                accounts.addAll(((AccountGroup) component).getAllAccounts());
            }
        }
        return accounts;
    }


}

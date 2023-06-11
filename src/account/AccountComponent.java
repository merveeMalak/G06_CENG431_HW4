package account;

import bank.CurrencyRate;

public abstract class AccountComponent {
    protected int id;

    public AccountComponent(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public abstract void getAccountInfo();

}

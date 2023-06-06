package account;

public abstract class Account extends AccountComponent{


    public Account(int id) {
        super(id);
    }

    public abstract void getBalance() ;


}

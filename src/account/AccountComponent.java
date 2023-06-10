package account;

public abstract class AccountComponent {
    protected int id;

    public AccountComponent(int id){
        this.id = id;
    }
    public abstract double getBalance();

    public abstract void calculateFutureBalance(int day);

    public int getId(){
        return id;
    }

    public abstract void getAccountInfo();

}

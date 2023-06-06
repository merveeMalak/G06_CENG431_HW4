package account;

public class Investment extends Account{
    public Investment(int id) {
        super(id);
    }

    @Override
    public void getBalance() {

    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    public void buyStocks(){}

    public void buyFunds(){}
    @Override
    public String toString() {
        return "Account " + super.id + ": Investment";
    }
}
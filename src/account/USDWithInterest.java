package account;

public class USDWithInterest extends Account{
    public USDWithInterest(int id) {
        super(id);
    }

    @Override
    public void getBalance() {

    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    @Override
    public String toString() {
        return "Account " + super.id + ": USD With Interest";
    }
}

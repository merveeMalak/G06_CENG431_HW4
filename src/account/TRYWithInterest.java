package account;

public class TRYWithInterest extends Account{
    public TRYWithInterest(int id) {
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
        return "Account " + super.id + ": TRY With Interest";
    }
}

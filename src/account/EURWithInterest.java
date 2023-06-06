package account;

public class EURWithInterest extends Account{
    public EURWithInterest(int id) {
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
        return "Account " + super.id + ": EUR With Interest";
    }
}

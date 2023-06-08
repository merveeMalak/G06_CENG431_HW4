package account;

public class EURWithInterest extends Account{
    public EURWithInterest(int id) {
        super(id);
    }

    @Override
    public String getType() {
        return "EURWithInterest";
    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    @Override
    public String toString() {
        return "Account " + super.id + ": EUR With Interest";
    }
}

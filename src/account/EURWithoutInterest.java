package account;

public class EURWithoutInterest extends Account{
    public EURWithoutInterest(int id) {
        super(id);
    }


    @Override
    public String getType() {
        return "EURWithoutInterest";
    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    @Override
    public String toString() {
        return "Account " + super.id + ": EUR Without Interest";
    }
}

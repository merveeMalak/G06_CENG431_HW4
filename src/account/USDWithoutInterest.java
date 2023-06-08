package account;

public class USDWithoutInterest extends Account{
    public USDWithoutInterest(int id) {
        super(id);
    }


    @Override
    public String getType() {
        return "USDWithoutInterest";
    }

    @Override
    public void calculateFutureBalance(int day) {

    }
    @Override
    public String toString() {
        return "Account " + super.id + ": USD Without Interest";
    }
}

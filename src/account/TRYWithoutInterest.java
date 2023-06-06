package account;

public class TRYWithoutInterest extends Account{
    public TRYWithoutInterest(int id) {
        super(id);
    }

    @Override
    public void getBalance() {

    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    public void depositMoney(){}

    @Override
    public String toString() {
        return "Account " + super.id + ": TRY Without Interest";
    }
}

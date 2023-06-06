package account;

public class XAUWithInterest extends Account{
    public XAUWithInterest(int id) {
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
        return "Account " + super.id + ": XAU With Interest";
    }
}
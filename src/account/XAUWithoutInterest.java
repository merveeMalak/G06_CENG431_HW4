package account;

public class XAUWithoutInterest extends Account{
    public XAUWithoutInterest(int id) {
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
        return "Account " + super.id + ": XAU Without Interest";
    }
}
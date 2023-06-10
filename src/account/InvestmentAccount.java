package account;

public class InvestmentAccount extends Account {
    public InvestmentAccount(int id) {
        super(id);
    }

    @Override
    public String getType() {
        return "Investment";
    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    @Override
    public void getAccountInfo() {
        System.out.println("ID: " + super.id + " - Account : Investment");
    }
}

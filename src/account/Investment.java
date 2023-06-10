package account;

public class Investment extends Account {
    public Investment(int id) {
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
        System.out.println("id: " + super.id + " - Account : Investment");

    }
}
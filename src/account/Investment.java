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

    public void buyStocks() {
    }

    public void buyFunds() {
    }

    @Override
<<<<<<< Updated upstream
    public String toString() {
        return "Account " + super.id + ": Investment";
=======
    public void getAccountInfo() {
        System.out.println("id: " + super.id + " - Account : Investment");
>>>>>>> Stashed changes
    }
}
package account;

public class XAUWithoutInterest extends Account {
    public XAUWithoutInterest(int id) {
        super(id);
    }


    @Override
    public String getType() {
        return "XAUWithoutInterest";
    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    @Override
<<<<<<< Updated upstream
    public String toString() {
        return "Account " + super.id + ": XAU Without Interest";
=======
    public void getAccountInfo() {
        System.out.println("id: " + super.id + "- Account : XAU Without Interest");
>>>>>>> Stashed changes
    }
}
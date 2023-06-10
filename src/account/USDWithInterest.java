package account;

public class USDWithInterest extends Account{
    public USDWithInterest(int id) {
        super(id);
    }


    @Override
    public String getType() {
        return "USDWithInterest";
    }

    @Override
    public void calculateFutureBalance(int day) {

    }
    @Override
    public void getAccountInfo() {
        System.out.println("id: " + super.id +" - Account : USD With Interest");
    }

}

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
    public void getAccountInfo() {
        System.out.println("id: " + super.id +" - Account : USD Without Interest");
    }

}

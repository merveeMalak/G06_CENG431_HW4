package account;

public class TRYWithInterest extends Account{
    public TRYWithInterest(int id) {
        super(id);
    }


    @Override
    public String getType() {
        return "TRYWithInterest";
    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    @Override
    public void getAccountInfo() {
        System.out.println("id: " + super.id +" - Account : TRY With Interest");
    }

}

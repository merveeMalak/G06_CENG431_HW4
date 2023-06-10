package account;

public class EURWithInterest extends Account{
    public EURWithInterest(int id) {
        super(id);
    }

    @Override
    public String getType() {
        return "EURWithInterest";
    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    @Override
    public void getAccountInfo() {
        System.out.println("id: " + super.id +" - Account : EUR With Interest");
    }

}

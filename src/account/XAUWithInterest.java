package account;

public class XAUWithInterest extends Account{
    public XAUWithInterest(int id) {
        super(id);
    }



    @Override
    public String getType() {
        return "XAUWithInterest";
    }

    @Override
    public void calculateFutureBalance(int day) {

    }
    @Override
    public void getAccountInfo() {
        System.out.println("id: " + super.id +" - Account : XAU With Interest");
    }

}
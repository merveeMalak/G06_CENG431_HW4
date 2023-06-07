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
    public void getAccountInfo() {
        System.out.println("id: " + super.id +"- Account : XAU Without Interest");
    }

}
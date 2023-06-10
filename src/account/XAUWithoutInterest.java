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


    public void getAccountInfo() {
        System.out.println("id: " + super.id + "- Account : XAU Without Interest");
    }

}
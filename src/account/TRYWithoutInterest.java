package account;

public class TRYWithoutInterest extends Account{
    public TRYWithoutInterest(int id) {
        super(id);
    }

    @Override
    public void getBalance() {

    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    public void depositMoney(){}

    @Override
    public void getAccountInfo() {
        System.out.println("id: " + super.id +" - Account : TRY Without Interest");
    }

}

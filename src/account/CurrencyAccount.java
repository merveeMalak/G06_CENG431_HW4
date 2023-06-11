package account;

import bank.CurrencyRate;

public class CurrencyAccount extends Account {
    private String currencyType;
    private boolean hasInterest;

    public CurrencyAccount(int id, String currencyType, boolean hasInterest) {
        super(id);
        this.currencyType = currencyType;
        this.hasInterest = hasInterest;
    }

    @Override
    public String getType() {
        return currencyType;
    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    @Override
    public void getAccountInfo() {
        System.out.printf("ID: %s - Account: %s %s Interest\n", super.getId(), this.currencyType, this.hasInterest ? "With" : "Without");
//        System.out.println("id: " + super.id + " - Account : TRY With Interest");
    }


    public boolean isHasInterest() {
        return hasInterest;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    public void increaseValue(double value) {
        this.value += value;
    }

    public boolean decreaseValue(double value) {
        if (value > this.value) {
            return false;
        } else {
            this.value -= value;
            return true;
        }
    }
}

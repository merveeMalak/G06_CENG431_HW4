package bank;

public class InterestRate {
    private String currencyType;
    private double interestRate;

    public InterestRate(String currencyType, double interestRate) {
        this.currencyType = currencyType;
        this.interestRate = interestRate;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate){
        this.interestRate = interestRate;
    }
}

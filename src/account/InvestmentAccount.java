package account;

import bank.CurrencyRate;
import bank.Fund;
import bank.Stock;

import java.util.ArrayList;
import java.util.List;

public class InvestmentAccount extends Account {
    private List<Fund> funds;
    private List<Stock> stocks;


    public InvestmentAccount(int id) {
        super(id);
        this.funds = new ArrayList<>();
        this.stocks = new ArrayList<>();
    }

    @Override
    public double getValue() {
        double totalValue = 0;
        for (Fund fund :
                this.funds) {
            totalValue += fund.getPrice();
        }
        for (Stock stock :
                this.stocks) {
            totalValue += stock.getPrice();
        }
        return totalValue;
    }

    @Override
    public String getType() {
        return "Investment";
    }

    @Override
    public void calculateFutureBalance(int day) {

    }

    @Override
    public void getAccountInfo() {
        System.out.println("ID: " + super.id + " - Account : Investment");
    }


    public void addFund(Fund fund) {
        this.funds.add(fund);
    }

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }

}

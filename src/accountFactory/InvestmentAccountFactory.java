package accountFactory;

import account.Account;
import account.Investment;

public class InvestmentAccountFactory extends AccountFactory{


    @Override
    public Account createAccount(int id,boolean hasInterest) {
        return new Investment(id);
    }
}

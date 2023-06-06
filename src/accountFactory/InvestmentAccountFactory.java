package accountFactory;

import account.Account;
import account.Investment;

public class InvestmentAccountFactory extends AccountFactory{


    @Override
    public Account createAccount(boolean hasInterest) {
        return new Investment();
    }
}

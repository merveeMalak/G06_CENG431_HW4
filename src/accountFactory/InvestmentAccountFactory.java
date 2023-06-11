package accountFactory;

import account.Account;
import account.InvestmentAccount;

public class InvestmentAccountFactory extends AccountFactory {

    @Override
    public Account createAccount(int id, String currency, boolean hasInterest) {
        return new InvestmentAccount(id);
    }
}

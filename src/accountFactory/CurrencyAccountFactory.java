package accountFactory;

import account.CurrencyAccount;

public class CurrencyAccountFactory extends AccountFactory {

    @Override
    public CurrencyAccount createAccount(int id, String currency, boolean hasInterest) {
        return new CurrencyAccount(id, currency, hasInterest);
    }
}

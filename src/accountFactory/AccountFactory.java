package accountFactory;

import account.Account;

public abstract class AccountFactory {
    public abstract Account createAccount(int id, String currency, boolean hasInterest);
}

package accountFactory;

import account.Account;

public abstract class AccountFactory {
    abstract Account createAccount(int id, String currency, boolean hasInterest);
}

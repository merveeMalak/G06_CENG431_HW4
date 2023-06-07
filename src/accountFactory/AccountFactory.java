package accountFactory;

import account.Account;

public abstract class  AccountFactory {

    public abstract Account createAccount(int id, boolean hasInterest);
}

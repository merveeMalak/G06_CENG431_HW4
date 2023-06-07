package accountFactory;

import account.Account;
import account.USDWithInterest;
import account.USDWithoutInterest;


public class USDAccountFactory extends  AccountFactory {


    public USDAccountFactory() {

    }

    @Override
    public Account createAccount(int id, boolean hasInterest) {
        if (hasInterest) {
            return new USDWithInterest(id);
        }
        return new USDWithoutInterest(id);
    }
}
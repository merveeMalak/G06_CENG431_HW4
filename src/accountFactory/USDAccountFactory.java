package accountFactory;

import account.Account;
import account.USDWithInterest;
import account.USDWithoutInterest;


public class USDAccountFactory extends   AccountFactory {


    public USDAccountFactory() {

    }

    @Override
    public Account createAccount(boolean hasInterest) {
        if (hasInterest) {
            return new USDWithInterest();
        }
        return new USDWithoutInterest();
    }
}
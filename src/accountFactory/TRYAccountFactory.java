package accountFactory;


import account.Account;
import account.TRYWithInterest;
import account.TRYWithoutInterest;

public class TRYAccountFactory extends  AccountFactory {

    public TRYAccountFactory() {}

    @Override
    public Account createAccount(boolean hasInterest) {
        if (hasInterest) {
            return new TRYWithInterest();
        }
        return new TRYWithoutInterest();
    }
}
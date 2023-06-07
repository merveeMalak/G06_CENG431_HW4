package accountFactory;


import account.Account;
import account.TRYWithInterest;
import account.TRYWithoutInterest;

public class TRYAccountFactory extends  AccountFactory {

    public TRYAccountFactory() {}

    @Override
    public Account createAccount(int id, boolean hasInterest) {
        if (hasInterest) {
            return new TRYWithInterest(id);
        }
        return new TRYWithoutInterest(id);
    }
}
package accountFactory;

import account.Account;
import account.XAUWithInterest;
import account.XAUWithoutInterest;

public class XAUAccountFactory extends AccountFactory{
    @Override
    public Account createAccount(boolean hasInterest) {
        if (hasInterest){
            return new XAUWithInterest();
        }
        return new XAUWithoutInterest();
    }
}

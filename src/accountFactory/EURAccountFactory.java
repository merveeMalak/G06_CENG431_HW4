package accountFactory;

import account.Account;
import account.EURWithInterest;
import account.EURWithoutInterest;

public class EURAccountFactory extends AccountFactory{

    public EURAccountFactory(){

    }

    @Override
    public Account createAccount( boolean hasInterest) {
        if (hasInterest) {
            return new EURWithInterest();
        }
        return new EURWithoutInterest();
    }


}

package accountFactory;

import account.Account;
import account.EURWithInterest;
import account.EURWithoutInterest;

public class EURAccountFactory extends AccountFactory{

    public EURAccountFactory(){

    }

    @Override
    public Account createAccount( int id, boolean hasInterest) {
        if (hasInterest) {
            return new EURWithInterest(id);
        }
        return new EURWithoutInterest(id);
    }


}

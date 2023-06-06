import account.AccountComponent;
import account.AccountGroup;
import account.AccountTree;
import accountFactory.AccountFactory;
import accountFactory.TRYAccountFactory;



public class Client extends User{
    private AccountFactory accountFactory;
    private AccountTree accountTree;

    public Client(){
        createFirstAccount();
    }
    private void createFirstAccount(){
        accountTree = new AccountTree();
        AccountGroup topAccountGroup = new AccountGroup(1,"top Account");
        accountFactory = new TRYAccountFactory();
        topAccountGroup.addAccount(accountFactory.createAccount(false));
        accountTree.insert(topAccountGroup, -1);
    }
    public AccountComponent getTopAccountGroup(){
        return accountTree.getRoot().getAccountComponent();
    }
    public void addAccountComponent(AccountComponent accountComponent){
        accountTree.insert(accountComponent, 1);
    }
}

import account.AccountComponent;
import account.AccountGroup;
import account.AccountTree;
import accountFactory.AccountFactory;
import accountFactory.TRYAccountFactory;

import java.util.ArrayList;
import java.util.List;


public class Client extends User{
    private AccountFactory accountFactory;
    //private AccountTree accountTree;
    private String clientName;
    private AccountComponent topAccountGroup;
    public Client(String clientName) {
        this.clientName = clientName;
        createFirstAccount();
    }
    private void createFirstAccount(){
        //accountTree = new AccountTree();
        topAccountGroup = new AccountGroup(0,"top Account");
        accountFactory = new TRYAccountFactory();
        addAccountComponent(accountFactory.createAccount(1,false));
    }


    public void addAccountComponent(AccountComponent account){
        if (topAccountGroup instanceof AccountGroup) {
            ((AccountGroup) topAccountGroup).addAccount(account);
        } else {
            System.out.println("Cannot add account to a non-group component.");
        }
    }

    public void addAccountToGroup(AccountComponent account, int id) {
        AccountComponent group = findAccountGroup(topAccountGroup, id);
        if (group != null && group instanceof AccountGroup) {
            ((AccountGroup) group).addAccount(account);
        } else {
            System.out.println("Account Group '" + id + "' not found.");
        }
    }

    public void changeAccountGroup(AccountComponent account, int newAccountId){
        AccountGroup currentGroup = findAccountGroupOfAccount(topAccountGroup, account);
        AccountComponent newGroup = findAccountGroup(topAccountGroup, newAccountId);

        if (currentGroup != null && newGroup != null && newGroup instanceof AccountGroup) {
            currentGroup.removeAccount(account);
            ((AccountGroup) newGroup).addAccount(account);
            System.out.println("Account moved to '" + ((AccountGroup) newGroup).getName() + "'.");
        } else {
            System.out.println("Failed to move account. Please check the group name.");
        }
    }
    private AccountComponent findAccountGroup(AccountComponent component, int id) {
        if (component instanceof AccountGroup && ((AccountGroup) component).getId()==(id)) {
            return component;
        }

        if (component instanceof AccountGroup) {
            for (AccountComponent child : ((AccountGroup) component).getAccounts()) {
                AccountComponent group = findAccountGroup(child, id);
                if (group != null) {
                    return group;
                }
            }
        }

        return null;
    }
    private AccountGroup findAccountGroupOfAccount(AccountComponent component, AccountComponent account) {
        if (component instanceof AccountGroup) {
            List<AccountComponent> accountComponents = ((AccountGroup) component).getAccounts();
            if (accountComponents.contains(account)) {
                return (AccountGroup) component;
            }
            for (AccountComponent child : accountComponents) {
                AccountGroup group = findAccountGroupOfAccount(child, account);
                if (group != null) {
                    return group;
                }
            }
        }
        return null;
    }

    public void getAccounts(){
        System.out.println(topAccountGroup);
    }

    public int getSizeOfAccountGroup(int id){
        return ((AccountGroup) findAccountGroup(topAccountGroup, id)).sizeAccountGroup();
    }

    public void getAccountsInfo() {
        System.out.println("Client: " + clientName);
        displayAccountHierarchy(topAccountGroup, 0);
    }
    private void displayAccountHierarchy(AccountComponent component, int level) {
        for (int i = 0; i < level; i++) {
            System.out.print("\t");
        }

        component.getAccountInfo();
        if (component instanceof AccountGroup) {
            List<AccountComponent> accountComponents = ((AccountGroup) component).getAccounts();
            for (AccountComponent child : accountComponents) {
                displayAccountHierarchy(child, level + 1);
            }
        }

    }

}

package user;

import account.AccountComponent;
import account.AccountGroup;
import account.CurrencyAccount;
import account.InvestmentAccount;
import accountFactory.AccountFactory;
import accountFactory.CurrencyAccountFactory;
import bank.Fund;
import bank.Stock;

import java.util.List;


public class Client extends User {
    private CurrencyAccountFactory accountFactory;

    private String clientName;

    public AccountComponent getTopAccountGroup() {
        return topAccountGroup;
    }

    private AccountComponent topAccountGroup;

    public String getClientName() {
        return clientName;
    }

    public Client(String clientName) {
        this.clientName = clientName;
        createFirstAccount();
    }

    private void createFirstAccount() {
        topAccountGroup = new AccountGroup(1, "top Account");
        accountFactory = new CurrencyAccountFactory();
        addAccountComponent(accountFactory.createAccount(2, "TRY", false));
    }


    public void addAccountComponent(AccountComponent account) {
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

    public boolean createAccount(AccountComponent newAccountComponent, int parentId) {
        if (parentId == 1) {
            addAccountComponent(newAccountComponent);
            return true;
        }
        AccountComponent accountGroup = getAccountComponentById(topAccountGroup, parentId);
        if (accountGroup instanceof AccountGroup) {
            addAccountToGroup(newAccountComponent, parentId);
            return true;
        }
        return false;
    }

    public boolean changeAccountGroup(int accountId, int newAccountId) {
        AccountComponent accountComponent = getAccountComponentById(topAccountGroup, accountId);
        AccountGroup currentGroup = findAccountGroupOfAccount(topAccountGroup, accountComponent);
        AccountComponent newGroup = findAccountGroup(topAccountGroup, newAccountId);
        if (currentGroup != null && newGroup instanceof AccountGroup) {
            if (!isDescendant(accountComponent,newGroup)) {
                currentGroup.removeAccount(accountComponent);
                ((AccountGroup) newGroup).addAccount(accountComponent);
                return true;
            }
            ((AccountGroup) accountComponent).removeAccount(newGroup);

            currentGroup.addAccount(newGroup);
            currentGroup.removeAccount(accountComponent);

            ((AccountGroup) newGroup).addAccount(accountComponent);
            return true;

        }
        return false;
    }

    private AccountComponent findAccountGroup(AccountComponent component, int id) {
        if (component instanceof AccountGroup && ((AccountGroup) component).getId() == (id)) {
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


    public AccountComponent getAccountComponentById(AccountComponent component, int id) {
        if (component.getId() == id) {
            return component;
        }

        if (component instanceof AccountGroup) {
            for (AccountComponent child : ((AccountGroup) component).getAccounts()) {
                AccountComponent group = getAccountComponentById(child, id);
                if (group != null) {
                    return group;
                }
            }
        }

        return null;
    }

    private boolean isDescendant(AccountComponent group, AccountComponent account) {
        if (group == account) {
            return true;
        }

        if (group instanceof AccountGroup) {
            for (AccountComponent child : ((AccountGroup) group).getAccounts()) {
                if (isDescendant(child, account)) {
                    return true;
                }
            }
        }

        return false;
    }


    public void getAccounts() {
        System.out.println(topAccountGroup);
    }

    public int getSizeOfAccountGroup(int id) {
        return ((AccountGroup) findAccountGroup(topAccountGroup, id)).sizeAccountGroup();
    }

    public void getAccountsInfo() {
        System.out.println("user.Client: " + clientName);
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

    public boolean buyStock(int accountId, Stock stock, int tryAccountId){
        AccountComponent tryAccount = getAccountComponentById(topAccountGroup, tryAccountId);
        AccountComponent account = getAccountComponentById(topAccountGroup, accountId);
        if (account instanceof InvestmentAccount && tryAccount instanceof CurrencyAccount && !((CurrencyAccount) tryAccount).isHasInterest()){
            if (((CurrencyAccount) tryAccount).decreaseValue(stock.getPrice())){
                ((InvestmentAccount) account).addStock(stock);
                return true;
            }
        }
        return false;
    }

    public boolean buyFund(int accountId, Fund fund, int tryAccountId){
        AccountComponent tryAccount = getAccountComponentById(topAccountGroup, tryAccountId);
        AccountComponent account = getAccountComponentById(topAccountGroup, accountId);
        if (account instanceof InvestmentAccount && tryAccount instanceof CurrencyAccount && !((CurrencyAccount) tryAccount).isHasInterest()){
            if (((CurrencyAccount) tryAccount).decreaseValue(fund.getPrice())){
                ((InvestmentAccount) account).addFund(fund);
                return true;
            }
        }
        return false;
    }

    public Stock sellStock(int accountId, int stockId, int tryAccountId){
        AccountComponent tryAccount = getAccountComponentById(topAccountGroup, tryAccountId);
        AccountComponent account = getAccountComponentById(topAccountGroup, accountId);
        if (account instanceof InvestmentAccount && tryAccount instanceof CurrencyAccount && !((CurrencyAccount) tryAccount).isHasInterest()){
            for (Stock stock :((InvestmentAccount) account).getStocks()){
                if (stock.getId() == stockId){
                    ((InvestmentAccount) account).removeStock(stock);
                    ((CurrencyAccount) tryAccount).increaseValue(stock.getPrice());
                    return stock;
                }
            }
        }
        return null;
    }

    public Fund sellFund(int accountId, int fundId, int tryAccountId){
        AccountComponent account = getAccountComponentById(topAccountGroup, accountId);
        AccountComponent tryAccount = getAccountComponentById(topAccountGroup, tryAccountId);
        if (account instanceof InvestmentAccount && tryAccount instanceof CurrencyAccount && !((CurrencyAccount) tryAccount).isHasInterest()){
            for (Fund fund :((InvestmentAccount) account).getFunds()){
                if (fund.getId() == fundId){
                    ((InvestmentAccount) account).removeFund(fund);
                    ((CurrencyAccount) tryAccount).increaseValue(fund.getPrice());
                    return fund;
                }
            }
        }
        return null;
    }
}

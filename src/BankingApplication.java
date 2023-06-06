import account.Account;
import account.AccountComponent;
import account.AccountGroup;
import accountFactory.AccountFactory;
import accountFactory.EURAccountFactory;
import accountFactory.InvestmentAccountFactory;

import java.util.ArrayList;
import java.util.List;

public class BankingApplication {
    public static void main(String[] args) {

        AccountFactory accountFactory = new InvestmentAccountFactory();
        Account account1 = accountFactory.createAccount(true);
        AccountFactory accountFactory1 = new EURAccountFactory();
        Account account2 = accountFactory1.createAccount(true);
        AccountGroup accountGroup = new AccountGroup("deneme");
        accountGroup.addAccount(account1);
        accountGroup.addAccount(account2);
        Client client = new Client();

        client.addAccountComponent(accountGroup);
        System.out.println(client.getTopAccountGroup());


    }
}
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
        Account account1 = accountFactory.createAccount(2,true);
        AccountFactory accountFactory1 = new EURAccountFactory();
        Account account2 = accountFactory1.createAccount(3,true);
        AccountGroup accountGroup = new AccountGroup(4,"deneme");
        Client client = new Client("merve");
        Account account3 = accountFactory1.createAccount(5,false);
        accountGroup.addAccount(account1);
        client.addAccountComponent(accountGroup);

        client.addAccountToGroup(account2, 4);
        client.addAccountComponent(account3);
        client.getAccountsInfo();
        System.out.println("*****************");
        client.changeAccountGroup(account3,4);
        client.getAccountsInfo();
        System.out.println("************************");
        AccountGroup accountGroup1 = new AccountGroup(6,"test");
        Account account4 = accountFactory1.createAccount(7,false);
        client.addAccountToGroup(accountGroup1, 4);
        client.addAccountToGroup(account4, 6);
        client.getAccountsInfo();
        System.out.println("****************");
        client.changeAccountGroup(account4,0 );
        client.getAccountsInfo();
        //client.addAccountComponentToTopGroup(accountGroup);
        //client.addAccountComponentToSpecificGroup(account1, 4);
        //client.addAccountComponentToSpecificGroup(account2, 4);
        //client.addAccountComponentToSpecificGroup(account3, 4);
        //client.getAccounts();
        //List<AccountComponent> accountComponents = client.getAccountsSpecificAccountGroup(4);
        //for (AccountComponent accountComponent : accountComponents){
        //    System.out.println(accountComponent);
        //}


    }
}
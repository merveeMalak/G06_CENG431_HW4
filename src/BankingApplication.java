import account.Account;
import account.AccountComponent;
import account.AccountGroup;
import account.AccountManager;
import account.EURWithoutInterest;
import accountFactory.AccountFactory;
import accountFactory.EURAccountFactory;
import accountFactory.InvestmentAccountFactory;

import java.util.ArrayList;
import java.util.List;

import java.util.List;

public class BankingApplication {
    public static void main(String[] args) {

        AccountManager accountManager = new AccountManager();
        accountManager.createClient("merve");
        accountManager.loginClient("merve");
        Client client = accountManager.getCurrentClient();
        AccountFactory accountFactory = new InvestmentAccountFactory();
<<<<<<< Updated upstream
        Account account1 = accountFactory.createAccount(true);
        AccountFactory accountFactory1 = new EURAccountFactory();
        Account account2 = accountFactory1.createAccount(true);
        AccountGroup accountGroup = new AccountGroup("deneme");
=======
        Account account1 = accountFactory.createAccount(2, true);
        AccountFactory accountFactory1 = new EURAccountFactory();
        Account account2 = accountFactory1.createAccount(3, false);
        AccountGroup accountGroup = new AccountGroup(4, "deneme");
        Account account3 = accountFactory1.createAccount(5, false);
>>>>>>> Stashed changes
        accountGroup.addAccount(account1);
        accountGroup.addAccount(account2);
        Client client = new Client();

        client.addAccountComponent(accountGroup);
<<<<<<< Updated upstream
        System.out.println(client.getTopAccountGroup());
=======
        client.addAccountToGroup(account2, 4);
        client.addAccountComponent(account3);
        client.getAccountsInfo();
        for (Account account :
                accountManager.getCurrentClient().getAccountsFiltered("EURWithoutInterest")) {
            System.out.printf("%s\n", account.getType());
        }
        System.out.printf("%s", accountManager.getCurrentClient().getAccountsFiltered("EURWithoutInterest").size());
//        Account starterAccount = (Account) client.getAccountComponentById(client.getTopAccountGroup(), 1);
//        starterAccount.increaseValue(900);
//        System.out.printf("%s\n", starterAccount.getBalance());
//        accountManager.exchange(1, 5, 200);
//        System.out.println("After");
//        System.out.printf("%s\n", starterAccount.getBalance());
//        System.out.printf("%s\n", account3.getBalance());


//        System.out.println("*****************");
//        client.changeAccountGroup(5,4);
//        client.getAccountsInfo();
//        System.out.println("************************");
//
//        AccountGroup accountGroup1 = new AccountGroup(6,"test");
//        Account account4 = accountFactory1.createAccount(7,false);
//        client.addAccountToGroup(accountGroup1, 4);
//        client.addAccountToGroup(account4, 6);
//        client.getAccountsInfo();
//        System.out.println("****************");
//        //client.changeAccountGroup(account4,0 );
//
//        Account account5 = accountFactory1.createAccount(8,false);
//        client.addAccountToGroup(account5, 6);
//        client.changeAccountGroup(6, 0);
//
//        client.getAccountsInfo();

        //client.addAccountComponentToTopGroup(accountGroup);
        //client.addAccountComponentToSpecificGroup(account1, 4);
        //client.addAccountComponentToSpecificGroup(account2, 4);
        //client.addAccountComponentToSpecificGroup(account3, 4);
        //client.getAccounts();
        //List<AccountComponent> accountComponents = client.getAccountsSpecificAccountGroup(4);
        //for (AccountComponent accountComponent : accountComponents){
        //    System.out.println(accountComponent);
        //}
>>>>>>> Stashed changes


    }
}
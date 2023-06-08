import account.Account;
import account.AccountGroup;
import accountFactory.AccountFactory;
import accountFactory.EURAccountFactory;
import accountFactory.InvestmentAccountFactory;
import user.Client;

public class BankingApplication {
    public static void main(String[] args) {
        BankingSimulation bankingSimulation = new BankingSimulation();
        bankingSimulation.startSimulation();
/*
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
        client.changeAccountGroup(5,4);
        client.getAccountsInfo();
        System.out.println("************************");

        AccountGroup accountGroup1 = new AccountGroup(6,"test");
        Account account4 = accountFactory1.createAccount(7,false);
        client.addAccountToGroup(accountGroup1, 4);
        client.addAccountToGroup(account4, 6);
        client.getAccountsInfo();
        System.out.println("****************");
        //client.changeAccountGroup(account4,0 );

        Account account5 = accountFactory1.createAccount(8,false);
        client.addAccountToGroup(account5, 6);
        client.changeAccountGroup(6, 0);

        client.getAccountsInfo();
        System.out.println(client.getSizeOfAccountGroup(0));
*/
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
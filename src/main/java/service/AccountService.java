package service;

import dao.CRUD_Account;
import model.Account;

import java.util.ArrayList;
import java.util.List;

public class AccountService {
    static List<Account> accounts = new ArrayList<>();

    public static void signup(Account account){
        accounts.add(account);
    }

    public static Account returnaccount(String username, String password){
        for (Account account: accounts) {
            if (account.getUsername().equals(username) && account.getPassword().equals(password)){
                return account;
            }
        }
        return null;
    }

    public static void editAccount(Account account){
        for (int i = 0; i < accounts.size(); i++){
            if (accounts.get(i).getUsername().equals(account.getUsername())){
                accounts.set(i, account);
                CRUD_Account.Update(account);
            }
        }
    }
}

package myapp.service.implementations.inMemory;

import myapp.model.Account;
import myapp.service.InterfaceAccountService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountService implements InterfaceAccountService {
    private static List<Account> accounts = new ArrayList<>(Arrays.asList(
            new Account( "Tom", "qwerty", "USER"),
            new Account( "Jerry", "asdfgh", "USER"),
            new Account( "1", "1", "USER")
    ));

    public static Account findAccount(Account check) {
        return accounts.stream().filter(x -> x.equals(check)).findFirst().orElse(null);
    }

    public static void updateAccount(String id, Account newAccount) {

    }

    public static void saveAccount(Account account) {
        accounts.add(account);
    }

    public static void deleteAccount(Account account) {

    }
}

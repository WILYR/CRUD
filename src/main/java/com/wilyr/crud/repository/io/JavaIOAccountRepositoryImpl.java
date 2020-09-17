package com.wilyr.crud.repository.io;

import com.wilyr.crud.model.Account;
import com.wilyr.crud.model.AccountStatus;
import com.wilyr.crud.repository.IAccountRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaIOAccountRepositoryImpl implements IAccountRepository {
    File file = new File("src/main/resources/accounts.txt");

    private List<Account> getAll() {
        List<Account> currentAccounts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            if (file.length() != 0) {
                String line = reader.readLine();
                while (line != null) {
                    String[] lineSplit = line.split(",");
                    Account account = new Account(lineSplit[0], lineSplit[1]);
                    if (lineSplit[2].equals("ACTIVE")) {
                        account.setAccountStatus(AccountStatus.ACTIVE);
                    } else if (lineSplit[2].equals("DELETED")) {
                        account.setAccountStatus(AccountStatus.DELETED);
                    } else if (lineSplit[2].equals("BANNED")) {
                        account.setAccountStatus(AccountStatus.BANNED);
                    }
                    currentAccounts.add(account);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return currentAccounts;
    }

    private void rewrite(List<Account> currentAccounts) {
        try (FileWriter writer = new FileWriter(file)) {
            for (Account i : currentAccounts) {
                writer.write(i.getLogin() + "," + i.getPassword() + "," + i.getAccountStatus() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public Account save(Account account) {
        List<Account> currentAccounts = new ArrayList<>(getAll());
        boolean isAccountSave = true;
        for (Account i : currentAccounts) {
            if (i.getLogin().equals(account.getLogin())) {
                account.setLogin(i.getLogin());
                isAccountSave = false;
                break;
            }
        }
        if (isAccountSave) {
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(account.getLogin() + "," + account.getPassword() + "," + account.getAccountStatus() + "\n");
            } catch (IOException e) {
                e.getMessage();
            }
        }
        return account;
    }

    public void delete(Account account) {
        List<Account> currentAccounts = new ArrayList<>(getAll());
        Account removeElement = null;
        for (Account j : currentAccounts) {
            if (j.getLogin().equals(account.getLogin())) {
                removeElement = j;
            }
        }
        if (removeElement == null) {
            System.out.println("Skill " + account.getLogin() + " isn't exist");
        } else {
            currentAccounts.remove(removeElement);
            rewrite(currentAccounts);
        }
    }

    public Account get(String login) {
        List<Account> currentAccounts = new ArrayList<>(getAll());
        for (Account i : currentAccounts) {
            if (i.getLogin().equals(login)) {
                return i;
            }
        }
        return null;
    }

    public Account updatePassword(Account account) {
        List<Account> currentAccounts = new ArrayList<>(getAll());
        for (Account i : currentAccounts) {
            if (i.getLogin().equals(account.getLogin())) {
                i.setPassword(account.getPassword());
            }
        }
        rewrite(currentAccounts);
        return account;
    }
}

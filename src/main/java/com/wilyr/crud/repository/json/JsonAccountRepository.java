package com.wilyr.crud.repository.json;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.wilyr.crud.model.Account;
import com.wilyr.crud.model.AccountStatus;
import com.wilyr.crud.repository.IAccountRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonAccountRepository implements IAccountRepository {
    File fileAccounts = new File("src/main/resources/json/accounts.json");

    public List<Account> getAll() {
        List<Account> currentAccount = new ArrayList<>();
        try (JsonReader reader = new JsonReader(new BufferedReader(new FileReader(fileAccounts)))) {
            reader.setLenient(true);
            if (fileAccounts.length() != 0) {
                String login = null, password = null;
                AccountStatus status = null;
                reader.beginArray();
                while (reader.hasNext()) {
                    reader.beginObject();
                    while (reader.hasNext()) {
                        String name = reader.nextName();
                        if (name.equals("login")) {
                            login = reader.nextString();
                        } else if (name.equals("password")) {
                            password = reader.nextString();
                        } else if (name.equals("status")) {
                            String str = reader.nextString();
                            if (str.equals("ACTIVE")) {
                                status = AccountStatus.ACTIVE;
                            }
                            if (str.equals("BANNED")) {
                                status = AccountStatus.BANNED;
                            }
                            if (str.equals("DELETED")) {
                                status = AccountStatus.DELETED;
                            }
                        } else {
                            reader.skipValue();
                        }
                    }
                    reader.endObject();
                    Account account = new Account(login, password);
                    account.setAccountStatus(status);
                    currentAccount.add(account);
                }
                reader.endArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentAccount;
    }

    @Override
    public Account save(Account account) {
        List<Account> accountList = new ArrayList<>(getAll());
        boolean isAccountSave = true;
        for (Account i : accountList) {
            if (i.getLogin().equals(account.getLogin())) {
                account.setLogin(i.getLogin());
                isAccountSave = false;
                break;
            }
        }
        if (isAccountSave) {
            accountList.add(account);
            rewrite(accountList);
        }
        return account;
    }

    @Override
    public void delete(Account account) {
        List<Account> accountList = new ArrayList<>(getAll());
        Account removeElement = null;
        for (Account j : accountList) {
            if (j.getLogin().equals(account.getLogin())) {
                removeElement = j;
            }
        }
        if (removeElement == null) {
            System.out.println("Skill " + account.getLogin() + " isn't exist");
        } else {
            accountList.remove(removeElement);
            rewrite(accountList);
        }
    }

    private void rewrite(List<Account> accountList) {
        try (JsonWriter writer = new JsonWriter(new BufferedWriter(new FileWriter(fileAccounts, false)))) {
            writer.beginArray();
            for (Account acc : accountList) {
                writer.beginObject();
                writer.name("login").value(acc.getLogin());
                writer.name("password").value(acc.getPassword());
                writer.name("status").value(acc.getAccountStatus().toString());
                writer.endObject();
            }
            writer.endArray();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Override
    public Account get(String login) {
        List<Account> currentAccounts = new ArrayList<>(getAll());
        for (Account i : currentAccounts) {
            if (i.getLogin().equals(login)) {
                return i;
            }
        }
        return null;
    }

    @Override
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

package com.wilyr.crud.controller.jsonController;

import com.wilyr.crud.model.Account;
import com.wilyr.crud.model.AccountStatus;
import com.wilyr.crud.repository.json.JsonAccountRepository;

public class JsonAccountController {
    JsonAccountRepository jsonAccountRepository = new JsonAccountRepository();

    public void save(String login, String password, String accountStatus) {
        Account account = new Account(login, password);
        switch (accountStatus) {
            case "ACTIVE":
                account.setAccountStatus(AccountStatus.ACTIVE);
                break;
            case "DELETED":
                account.setAccountStatus(AccountStatus.DELETED);
                break;
            case "BANNED":
                account.setAccountStatus(AccountStatus.BANNED);
                break;
            default:
                System.out.println("Wrong status");
                return;
        }
        jsonAccountRepository.save(account);
    }

    public void delete(String loginForDelete) {
        if (jsonAccountRepository.get(loginForDelete) == null) {
            System.out.println("Wrong account");
        } else {
            Account account = new Account();
            account.setLogin(loginForDelete);
            jsonAccountRepository.delete(account);
        }
    }

    public void updatePassword(String login, String newPassword) {
        if (jsonAccountRepository.get(login) == null) {
            System.out.println("Wrong account");
        } else {
            Account account = new Account();
            account.setLogin(login);
            account.setPassword(newPassword);
            jsonAccountRepository.updatePassword(account);
        }
    }

    public void get(String login) {
        if (jsonAccountRepository.get(login) == null) {
            System.out.println("Wrong account");
        } else {
            System.out.println(jsonAccountRepository.get(login));
        }
    }
}

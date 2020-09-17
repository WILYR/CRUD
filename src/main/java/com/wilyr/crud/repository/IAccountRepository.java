package com.wilyr.crud.repository;

import com.wilyr.crud.model.Account;

public interface IAccountRepository extends GenericRepository {
    Account save(Account account);

    void delete(Account account);

    Account get(String login);

    Account updatePassword(Account account);
}

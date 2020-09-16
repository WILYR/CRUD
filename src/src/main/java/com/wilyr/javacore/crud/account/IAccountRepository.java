package src.main.java.com.wilyr.javacore.crud.account;

import src.main.java.com.wilyr.javacore.crud.GenericRepository;

public interface IAccountRepository extends GenericRepository {
    Account save(Account account);
    void delete(Account account);
    Account get(String login);
    Account updatePassword(Account account);
}

package src.main.java.com.wilyr.javacore.crud.account;

public class AccountController {
    AccountRepository accountRepository = new AccountRepository();
    public void save(Account account) {
        accountRepository.save(account);
    }

    public void delete(Account account) {
        accountRepository.delete(account);
    }
    public void updatePassword(Account account){
        accountRepository.updatePassword(account);
    }

    public void get(String login) {
        System.out.println(accountRepository.get(login));
    }
}

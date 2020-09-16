package src.main.java.com.wilyr.javacore.crud.account;

public class AccountController {
    AccountRepository accountRepository = new AccountRepository();

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
        accountRepository.save(account);
    }

    public void delete(String loginForDelete) {
        if (accountRepository.get(loginForDelete) == null) {
            System.out.println("Wrong account");
        } else {
            Account account = new Account();
            account.setLogin(loginForDelete);
            accountRepository.delete(account);
        }
    }

    public void updatePassword(String login, String newPassword) {
        if (accountRepository.get(login) == null) {
            System.out.println("Wrong account");
        } else {
            Account account = new Account();
            account.setLogin(login);
            account.setPassword(newPassword);
            accountRepository.updatePassword(account);
        }
    }

    public void get(String login) {
        if (accountRepository.get(login) == null) {
            System.out.println("Wrong account");
        } else {
            System.out.println(accountRepository.get(login));
        }
    }
}

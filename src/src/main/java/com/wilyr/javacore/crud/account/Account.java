package src.main.java.com.wilyr.javacore.crud.account;

public class Account {
    private String login;
    private String password;
    private AccountStatus accountStatus;

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Account(){
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }
}

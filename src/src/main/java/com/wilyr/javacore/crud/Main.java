package src.main.java.com.wilyr.javacore.crud;

import org.omg.PortableInterceptor.ACTIVE;
import src.main.java.com.wilyr.javacore.crud.account.Account;
import src.main.java.com.wilyr.javacore.crud.account.AccountRepository;
import src.main.java.com.wilyr.javacore.crud.account.AccountStatus;
import src.main.java.com.wilyr.javacore.crud.account.AccountView;

public class Main {

    public static void main(String[] args) {
        AccountView accountView = new AccountView();
        accountView.accountMenu();
    }
}

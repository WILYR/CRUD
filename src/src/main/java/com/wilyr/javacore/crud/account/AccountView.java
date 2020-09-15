package src.main.java.com.wilyr.javacore.crud.account;

import src.main.java.com.wilyr.javacore.crud.Skill;
import src.main.java.com.wilyr.javacore.crud.SkillController;

import java.util.Scanner;

public class AccountView {
    public void accountMenu() {
        AccountController accountController = new AccountController();
        Scanner in = new Scanner(System.in);
        int accountMenuNumber;
        do {
            System.out.println("1.Add account");
            System.out.println("2.Delete account");
            System.out.println("3.Update password");
            System.out.println("4.Get account");
            System.out.println("0.Exit");
            System.out.print("Your choice: ");
            accountMenuNumber = in.nextInt();
            switch (accountMenuNumber) {
                case 1:
                    Account account = new Account();
                    System.out.println("Add account login: ");
                    account.setLogin(in.next());
                    System.out.println("Add account password: ");
                    account.setPassword(in.next());
                    System.out.println("Add account status: ");
                    String str = in.next();
                    if (str.equals("ACTIVE")) {
                        account.setAccountStatus(AccountStatus.ACTIVE);
                    } else if (str.equals("DELETED")) {
                        account.setAccountStatus(AccountStatus.DELETED);
                    } else if(str.equals("BANNED")){
                        account.setAccountStatus(AccountStatus.BANNED);
                    } else {
                        System.out.println("Wrong status");
                        break;
                    }
                    accountController.save(account);
                    break;
                case 2:
                    System.out.print("Delete account(BY LOGIN): ");
                    Account accountForDelete = new Account();
                    accountForDelete.setLogin(in.next());
                    accountController.delete(accountForDelete);
                    break;
                case 3:
                    System.out.print("Update account password(BY LOGIN): ");
                    Account accountForUpdate = new Account();
                    accountForUpdate.setLogin(in.next());
                    System.out.print("New Password: ");
                    accountForUpdate.setPassword(in.next());
                    accountController.updatePassword(accountForUpdate);
                    break;
                case 4:
                    System.out.print("Get account(BY LOGIN): ");
                    accountController.get(in.next());
                    break;
            }
        } while (accountMenuNumber != 0);
    }
}

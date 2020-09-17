package com.wilyr.crud.view;

import java.util.Scanner;

public class CommonView {
    DeveloperView developerView = new DeveloperView();
    SkillView skillView = new SkillView();
    AccountView accountView = new AccountView();

    public void showMainMenu() {
        Scanner in = new Scanner(System.in);
        int menu;
        do {
            System.out.println("1.Account menu");
            System.out.println("2.Skill menu");
            System.out.println("3.Developer menu");
            System.out.println("0.Exit");
            System.out.print("Your choice: ");
            menu = in.nextInt();
            switch (menu) {
                case 1:
                    accountView.accountMenu();
                    break;
                case 2:
                    skillView.skillMenu();
                    break;
                case 3:
                    developerView.developerMenu();
                    break;
            }
        } while (menu != 0);
    }
}

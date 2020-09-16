package src.main.java.com.wilyr.javacore.crud.developer;

import java.util.Scanner;

public class DeveloperView {
    public void developerMenu() {
        DeveloperController developerController = new DeveloperController();
        Scanner in = new Scanner(System.in);
        int developerMenuNumber;
        do {
            System.out.println("1.Add developer");
            System.out.println("2.Delete developer");
            System.out.println("3.Update developer skills");
            System.out.println("4.Get developer");
            System.out.println("0.Back");
            System.out.print("Your choice: ");
            developerMenuNumber = in.nextInt();
            switch (developerMenuNumber) {
                case 1:
                    System.out.print("Set account for developer(Acc login): ");
                    String login = in.next();
                    System.out.print("Set skills for developer(Id through ','): ");
                    String listSkills = in.next();
                    developerController.save(login, listSkills);
                    break;
                case 2:
                    System.out.print("Set account login for Developer: ");
                    String loginForRemove = in.next();
                    developerController.delete(loginForRemove);
                    break;
                case 3:
                    System.out.print("Set account login for update: ");
                    String loginForUpdate = in.next();
                    System.out.print("Set new developer skills(Id through ','): ");
                    String newSkills = in.next();
                    developerController.update(loginForUpdate, newSkills);
                    break;
                case 4:
                    System.out.print("Set Developer login: ");
                    String loginForGet = in.next();
                    developerController.get(loginForGet);
                    break;
            }
        } while (developerMenuNumber != 0);
    }
}

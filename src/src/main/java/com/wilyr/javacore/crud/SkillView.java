package src.main.java.com.wilyr.javacore.crud;

import java.util.Scanner;

public class SkillView {
    public void skillMenu() {
        SkillController skillController = new SkillController();
        Scanner in = new Scanner(System.in);
        int skillMenuNumber;
        do {
            System.out.println("1.Add skill");
            System.out.println("2.Delete skill");
            System.out.println("3.Update skill");
            System.out.println("4.Get skill");
            System.out.println("0.Exit");
            System.out.print("Your choice: ");
            skillMenuNumber = in.nextInt();
            switch (skillMenuNumber) {
                case 1:
                    System.out.print("Add skill: ");
                    skillController.save(new Skill(in.next()));
                    break;
                case 2:
                    System.out.print("Delete skill(BY NAME): ");
                    skillController.delete(new Skill(in.next()));
                    break;
                case 3:
                    System.out.print("Update skill(BY ID): ");
                    Long id = in.nextLong();
                    System.out.print("New skill name: ");
                    String str = in.next();
                    skillController.update(id, str);
                    break;
                case 4:
                    System.out.print("Get skill(BY ID): ");
                    skillController.get(in.nextLong());
                    break;
            }
        } while (skillMenuNumber != 0);
    }
}

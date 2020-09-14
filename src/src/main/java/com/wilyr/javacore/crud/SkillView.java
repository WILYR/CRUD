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
            skillMenuNumber = in.nextInt();
            switch (skillMenuNumber) {
                case 1:
                    skillController.save();
                    break;
                case 2:
                    skillController.delete();
                    break;
                case 3:
                    skillController.update();
                    break;
                case 4:
                    skillController.get();
                    break;
            }
        } while (skillMenuNumber != 0);
    }
}

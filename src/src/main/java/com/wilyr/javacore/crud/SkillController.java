package src.main.java.com.wilyr.javacore.crud;

import java.util.Scanner;

public class SkillController {
    Scanner scanner = new Scanner(System.in);
    SkillsRepository skillsRepository = new SkillsRepository();

    public void save() {
        System.out.print("Add skill: ");
        skillsRepository.save(new Skill(scanner.next()));
    }

    public void delete() {
        System.out.print("Delete skill: ");
        skillsRepository.delete(new Skill(scanner.next()));
    }

    public void get() {
        System.out.print("Get skill: ");
        System.out.println(skillsRepository.get(scanner.nextLong()));
    }

    public void update() {
        System.out.print("Update skill(id): ");
        Skill skillForUpdate = skillsRepository.get(scanner.nextLong());
        System.out.print("\nNew skill name: ");
        skillForUpdate.setName(scanner.next());
        skillsRepository.update(skillForUpdate);
    }

}

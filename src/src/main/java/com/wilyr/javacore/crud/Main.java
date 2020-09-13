package src.main.java.com.wilyr.javacore.crud;

public class Main {

    public static void main(String[] args) {
        SkillsRepository skillsRepository = new SkillsRepository();
        Skill skill = new Skill("Maven");
        Skill skill2 = new Skill("Core");
        Skill skill3 = new Skill("SQL");
        Skill skill4 = new Skill("Git");
        skillsRepository.save(skill);
        skillsRepository.save(skill2);
        skillsRepository.save(skill3);
        skillsRepository.delete(skill2);
        skillsRepository.save(skill4);
        skill4.setName("wbwewrb");
        skillsRepository.update(skill4);
    }
}

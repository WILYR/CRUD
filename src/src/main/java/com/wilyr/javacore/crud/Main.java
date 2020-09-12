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
        skillsRepository.delete(skill2);
        skill.setName("Gradle");
        skillsRepository.update(skill);
        skillsRepository.get(skill.getId());
        skillsRepository.save(skill3);
        skillsRepository.save(skill4);
    }
}

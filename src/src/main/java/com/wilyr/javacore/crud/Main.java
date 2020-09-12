package src.main.java.com.wilyr.javacore.crud;

public class Main {

    public static void main(String[] args) {
        SkillsRepository skillsRepository = new SkillsRepository();
        Skills skill = new Skills(1, "Maven");
        Skills skill2 = new Skills(2, "Core");
        skillsRepository.save(skill);
        skillsRepository.save(skill2);
        skillsRepository.delete(skill2);
        skill.setName("Gradle");
        skillsRepository.update(skill);
        skillsRepository.get(skill.getId());

    }
}

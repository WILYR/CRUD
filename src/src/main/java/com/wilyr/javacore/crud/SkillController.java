package src.main.java.com.wilyr.javacore.crud;

public class SkillController {
    SkillsRepository skillsRepository = new SkillsRepository();

    public void save(Skill skill) {
        skillsRepository.save(skill);
    }

    public void delete(Skill skill) {
        skillsRepository.delete(skill);
    }

    public void get(Long id) {
        if (skillsRepository.get(id) == null) {
            System.out.println("Skill isn't exist");
        } else {
            System.out.println(skillsRepository.get(id).getId() + "," + skillsRepository.get(id).getName());
        }
    }

    public void update(Long id, String str) {
        Skill skill = skillsRepository.get(id);
        if (skill == null) {
            System.out.println("Skill isn't exist");
        } else {
            skill.setName(str);
            skillsRepository.update(skill);
        }
    }

}

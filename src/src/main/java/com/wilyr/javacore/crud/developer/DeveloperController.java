package src.main.java.com.wilyr.javacore.crud.developer;

import src.main.java.com.wilyr.javacore.crud.account.AccountRepository;
import src.main.java.com.wilyr.javacore.crud.skill.Skill;
import src.main.java.com.wilyr.javacore.crud.skill.SkillsRepository;

import java.util.ArrayList;
import java.util.List;

public class DeveloperController {
    public void save(String login, String listSkills) {
        AccountRepository accountRepository = new AccountRepository();
        if(accountRepository.get(login) == null) {
            System.out.println("Account isn't exist");
            return;
        }
        SkillsRepository skillsRepository = new SkillsRepository();
        List<Skill> skillsForSave = new ArrayList<>();
        String[] splitListSkills = listSkills.split(",");
        for (String i : splitListSkills) {
            if (skillsRepository.get(Long.parseLong(i)) != null) ;
            skillsForSave.add(skillsRepository.get(Long.parseLong(i)));
        }
        Developer developer = new Developer(skillsForSave, accountRepository.get(login));
        DeveloperRepository developerRepository = new DeveloperRepository();
        developerRepository.save(developer);
    }

    public void delete(String login) {
        DeveloperRepository developerRepository = new DeveloperRepository();
        if(developerRepository.get(login) == null) {
            System.out.println("Account isn't exist");
            return;
        }
        developerRepository.delete(developerRepository.get(login));
    }

    public void update(String login, String newSkills) {
        DeveloperRepository developerRepository = new DeveloperRepository();
        if(developerRepository.get(login) == null) {
            System.out.println("Account isn't exist");
            return;
        }
        SkillsRepository skillsRepository = new SkillsRepository();
        String[] splitNewSkills = newSkills.split(",");
        List<Skill> skillsForUpdate = new ArrayList<>();
        for (String i : splitNewSkills) {
            if (skillsRepository.get(Long.parseLong(i)) != null) {
                skillsForUpdate.add(skillsRepository.get(Long.parseLong(i)));
            }
        }
        developerRepository.update(developerRepository.get(login), skillsForUpdate);
    }

    public void get(String login) {
        DeveloperRepository developerRepository = new DeveloperRepository();
        if(developerRepository.get(login) == null) {
            System.out.println("Account isn't exist");
            return;
        }
        System.out.println(developerRepository.get(login));
    }
}

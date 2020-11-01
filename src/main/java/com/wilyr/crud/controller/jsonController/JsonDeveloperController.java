package com.wilyr.crud.controller.jsonController;

import com.wilyr.crud.model.Developer;
import com.wilyr.crud.model.Skill;
import com.wilyr.crud.repository.json.JsonAccountRepository;
import com.wilyr.crud.repository.json.JsonDeveloperRepository;
import com.wilyr.crud.repository.json.JsonSkillsRepository;

import java.util.ArrayList;
import java.util.List;

public class JsonDeveloperController {

    JsonDeveloperRepository jsonDeveloperRepository = new JsonDeveloperRepository();
    JsonSkillsRepository jsonSkillsRepository = new JsonSkillsRepository();

    public void save(String login, String listSkills) {
        JsonAccountRepository accountRepository = new JsonAccountRepository();
        if (accountRepository.get(login) == null) {
            System.out.println("Account isn't exist");
            return;
        }
        List<Skill> skillsForSave = new ArrayList<>();
        String[] splitListSkills = listSkills.split(",");
        for (String i : splitListSkills) {
            if (jsonSkillsRepository.get(Long.parseLong(i)) != null) ;
            skillsForSave.add(jsonSkillsRepository.get(Long.parseLong(i)));
        }
        Developer developer = new Developer(skillsForSave, accountRepository.get(login));
        jsonDeveloperRepository.save(developer);
    }

    public void delete(String login) {
        if (jsonDeveloperRepository.get(login) == null) {
            System.out.println("Account isn't exist");
            return;
        }
        jsonDeveloperRepository.delete(jsonDeveloperRepository.get(login));
    }

    public void update(String login, String newSkills) {
        if (jsonDeveloperRepository.get(login) == null) {
            System.out.println("Account isn't exist");
            return;
        }
        String[] splitNewSkills = newSkills.split(",");
        List<Skill> skillsForUpdate = new ArrayList<>();
        for (String i : splitNewSkills) {
            if (jsonSkillsRepository.get(Long.parseLong(i)) != null) {
                skillsForUpdate.add(jsonSkillsRepository.get(Long.parseLong(i)));
            }
        }
        jsonDeveloperRepository.update(jsonDeveloperRepository.get(login), skillsForUpdate);
    }

    public void get(String login) {
        if (jsonDeveloperRepository.get(login) == null) {
            System.out.println("Account isn't exist");
            return;
        }
        System.out.println(jsonDeveloperRepository.get(login));
    }
}

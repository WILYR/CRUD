package com.wilyr.crud.controller.ioController;

import com.wilyr.crud.model.Developer;
import com.wilyr.crud.model.Skill;
import com.wilyr.crud.repository.IDeveloperRepository;
import com.wilyr.crud.repository.ISkillsRepository;
import com.wilyr.crud.repository.io.JavaIOAccountRepositoryImpl;
import com.wilyr.crud.repository.io.JavaIODeveloperRepositoryImpl;
import com.wilyr.crud.repository.io.JavaIOSkillsRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class DeveloperController {
    IDeveloperRepository developerRepository = new JavaIODeveloperRepositoryImpl();
    ISkillsRepository javaIOSkillsRepositoryImpl = new JavaIOSkillsRepositoryImpl();

    public void save(String login, String listSkills) {
        JavaIOAccountRepositoryImpl accountRepository = new JavaIOAccountRepositoryImpl();
        if (accountRepository.get(login) == null) {
            System.out.println("Account isn't exist");
            return;
        }
        JavaIOSkillsRepositoryImpl javaIOSkillsRepositoryImpl = new JavaIOSkillsRepositoryImpl();
        List<Skill> skillsForSave = new ArrayList<>();
        String[] splitListSkills = listSkills.split(",");
        for (String i : splitListSkills) {
            if (javaIOSkillsRepositoryImpl.get(Long.parseLong(i)) != null) ;
            skillsForSave.add(javaIOSkillsRepositoryImpl.get(Long.parseLong(i)));
        }
        Developer developer = new Developer(skillsForSave, accountRepository.get(login));
        developerRepository.save(developer);
    }

    public void delete(String login) {
        if (developerRepository.get(login) == null) {
            System.out.println("Account isn't exist");
            return;
        }
        developerRepository.delete(developerRepository.get(login));
    }

    public void update(String login, String newSkills) {
        if (developerRepository.get(login) == null) {
            System.out.println("Account isn't exist");
            return;
        }
        String[] splitNewSkills = newSkills.split(",");
        List<Skill> skillsForUpdate = new ArrayList<>();
        for (String i : splitNewSkills) {
            if (javaIOSkillsRepositoryImpl.get(Long.parseLong(i)) != null) {
                skillsForUpdate.add(javaIOSkillsRepositoryImpl.get(Long.parseLong(i)));
            }
        }
        developerRepository.update(developerRepository.get(login), skillsForUpdate);
    }

    public void get(String login) {
        if (developerRepository.get(login) == null) {
            System.out.println("Account isn't exist");
            return;
        }
        System.out.println(developerRepository.get(login));
    }
}

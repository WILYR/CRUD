package com.wilyr.crud.controller;

import com.wilyr.crud.model.Skill;
import com.wilyr.crud.repository.ISkillsRepository;
import com.wilyr.crud.repository.io.JavaIOSkillsRepositoryImpl;

public class SkillController {
    ISkillsRepository javaIOSkillsRepositoryImpl = new JavaIOSkillsRepositoryImpl();

    public void save(Skill skill) {
        javaIOSkillsRepositoryImpl.save(skill);
    }

    public void delete(Skill skill) {
        if (javaIOSkillsRepositoryImpl.get(skill.getId()) == null) {
            System.out.println("Skill isn't exist");
        } else {
            javaIOSkillsRepositoryImpl.delete(skill);
        }
    }

    public void get(Long id) {
        if (javaIOSkillsRepositoryImpl.get(id) == null) {
            System.out.println("Skill isn't exist");
        } else {
            System.out.println(javaIOSkillsRepositoryImpl.get(id).getId() + "," + javaIOSkillsRepositoryImpl.get(id).getName());
        }
    }

    public void update(Long id, String str) {
        Skill skill = javaIOSkillsRepositoryImpl.get(id);
        if (skill == null) {
            System.out.println("Skill isn't exist");
        } else {
            skill.setName(str);
            javaIOSkillsRepositoryImpl.update(skill);
        }
    }

}

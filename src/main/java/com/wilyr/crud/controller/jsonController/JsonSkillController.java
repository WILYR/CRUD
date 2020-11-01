package com.wilyr.crud.controller.jsonController;

import com.wilyr.crud.model.Skill;
import com.wilyr.crud.repository.json.JsonSkillsRepository;

public class JsonSkillController {
    JsonSkillsRepository jsonSkillsRepository = new JsonSkillsRepository();

    public void save(Skill skill) {
        jsonSkillsRepository.save(skill);
    }

    public void delete(Skill skill) {
        jsonSkillsRepository.delete(skill);
    }

    public void get(Long id) {
        if (jsonSkillsRepository.get(id) == null) {
            System.out.println("Skill isn't exist");
        } else {
            System.out.println(jsonSkillsRepository.get(id).getId() + "," + jsonSkillsRepository.get(id).getName());
        }
    }

    public void update(Long id, String str) {
        Skill skill = jsonSkillsRepository.get(id);
        if (skill == null) {
            System.out.println("Skill isn't exist");
        } else {
            skill.setName(str);
            jsonSkillsRepository.update(skill);
        }
    }
}

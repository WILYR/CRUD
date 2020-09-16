package src.main.java.com.wilyr.javacore.crud.developer;

import src.main.java.com.wilyr.javacore.crud.GenericRepository;
import src.main.java.com.wilyr.javacore.crud.skill.Skill;

import java.util.List;

public interface IDeveloperRepository extends GenericRepository {
    Developer save(Developer developer);

    void delete(Developer developer);

    Developer get(String login);

    Developer update(Developer developer, List<Skill> newSkills);
}

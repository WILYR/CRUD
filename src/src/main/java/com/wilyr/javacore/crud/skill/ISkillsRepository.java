package src.main.java.com.wilyr.javacore.crud.skill;

import src.main.java.com.wilyr.javacore.crud.GenericRepository;

public interface ISkillsRepository extends GenericRepository {
    Skill save(Skill skill);

    Skill update(Skill skill);

    void delete(Skill skill);

    Skill get(long id);
}

package src.main.java.com.wilyr.javacore.crud;

public interface ISkillsRepository extends GenericRepository<Skill, Long>{
    Skill save(Skill skill);

    Skill update(Skill skill);

    void delete(Skill skill);

    Skill get(long id);
}

package src.main.java.com.wilyr.javacore.crud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SkillsRepository {

    List<Skill> skillsRepository = new ArrayList<>();

    public List<Skill> getAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader("skills.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] lineSplit = line.split(",");
                Skill skill = new Skill(Long.parseLong(lineSplit[0]), lineSplit[1]);
                skillsRepository.add(skill);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return skillsRepository;
    }

    public Skill save(Skill skill) {
        if (!skillsRepository.contains(skill.getId())) {
            skillsRepository.add(skill);
            try (FileWriter writer = new FileWriter("skills.txt", true)) {
                writer.write(skill.getId() + "," + skill.getName() + "\n");
            } catch (IOException e) {
                e.getMessage();
            }
        }
        return skill;
    }

    public void delete(Skill skill) {
        skillsRepository.remove(skill);
        try (FileWriter writer = new FileWriter("skills.txt")) {
            for (Skill i: skillsRepository) {
                writer.write(i.getId() + "," + i.getName() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public Skill get(long id) {
        Skill result = new Skill(0, "No result");
        for(Skill i: skillsRepository) {
            if(i.getId() == id) {
                return result = i;
            }
        }
        return result;
    }

    public Skill update(Skill skill) {
        delete(skill);
        return save(skill);
    }

}

package src.main.java.com.wilyr.javacore.crud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class SkillsRepository {

    List<Skills> skillsRepository = new ArrayList<>();

    public List<Skills> getAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader("skills.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] lineSplit = line.split(",");
                Skills skill = new Skills(Integer.parseInt(lineSplit[0]), lineSplit[1]);
                skillsRepository.add(skill);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return skillsRepository;
    }

    public Skills save(Skills skill) {
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

    public void delete(Skills skill) {
        skillsRepository.remove(skill);
        try (FileWriter writer = new FileWriter("skills.txt")) {
            for (Skills i: skillsRepository) {
                writer.write(i.getId() + "," + i.getName() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public Skills get(int id) {
        Skills result = new Skills(0, "No result");
        for(Skills i: skillsRepository) {
            if(i.getId() == id) {
                return result = i;
            }
        }
        return result;
    }

    public Skills update(Skills skill) {
        delete(skill);
        return save(skill);
    }

}

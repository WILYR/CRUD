package src.main.java.com.wilyr.javacore.crud;

import java.io.*;
import java.util.*;

public class SkillsRepository {

    List<Skill> skillsRepository = new ArrayList<>();

    public List<Skill> getAll() {
        File file = new File("skills.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            if (file.length() != 0) {
                String line = reader.readLine();
                while (line != null) {
                    String[] lineSplit = line.split(",");
                    Skill skill = new Skill(lineSplit[1]);
                    skill.setId(Long.parseLong(lineSplit[0]));
                    skillsRepository.add(skill);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return skillsRepository;
    }

    private long maxId() {
        List<Long> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("skills.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] lineSplit = line.split(",");
                list.add(Long.parseLong(lineSplit[0]));
                line = reader.readLine();
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if (list.isEmpty()) {
            return 0 + 1;
        } else {
            return Collections.max(list) + 1;
        }
    }

    public Skill save(Skill skill) {
        boolean isSkillSave = true;
        for (Skill i : skillsRepository) {
            if (i.getName().equals(skill.getName())) {
                isSkillSave = false;
                break;
            }
        }
        if (isSkillSave) {
            skill.setId(maxId());
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
            for (Skill i : skillsRepository) {
                writer.write(i.getId() + "," + i.getName() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public Skill get(long id) {
        Skill result = new Skill("No result");
        for (Skill i : skillsRepository) {
            if (i.getId() == id) {
                return result = i;
            }
        }
        return result;
    }

    public Skill update(Skill skill) {
        for (Skill i : skillsRepository) {
            if (i.getId() == (skill.getId())) {
                i.setName(skill.getName());
            }
        }
        try (FileWriter writer = new FileWriter("skills.txt")) {
            for (Skill i : skillsRepository) {
                writer.write(i.getId() + "," + i.getName() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return skill;
    }
}

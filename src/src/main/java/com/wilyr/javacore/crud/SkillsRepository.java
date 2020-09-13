package src.main.java.com.wilyr.javacore.crud;

import java.io.*;
import java.util.*;

public class SkillsRepository {

    private List<Skill> getAll() {
        List<Skill> skillsRepository = new ArrayList<>();
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
            return 1;
        } else {
            return Collections.max(list) + 1;
        }
    }

    public Skill save(Skill skill) {
        List<Skill> skillsRepository = new ArrayList<>(getAll());
        boolean isSkillSave = true;
        for (Skill i : skillsRepository) {
            if (i.getName().equals(skill.getName())) {
                skill.setId(i.getId());
                isSkillSave = false;
                break;
            }
        }
        if (isSkillSave) {
            skill.setId(maxId());
            try (FileWriter writer = new FileWriter("skills.txt", true)) {
                writer.write(skill.getId() + "," + skill.getName() + "\n");
            } catch (IOException e) {
                e.getMessage();
            }
        }
        return skill;
    }

    public void delete(Skill skill) {
        List<Skill> skillsRepository = new ArrayList<>(getAll());
        Skill removeElement = null;
        for (Skill j : skillsRepository) {
            if (j.getName().equals(skill.getName())) {
                removeElement = j;
            }
        }
        skillsRepository.remove(removeElement);

        try (FileWriter writer = new FileWriter("skills.txt")) {
            for (Skill i : skillsRepository) {
                writer.write(i.getId() + "," + i.getName() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public Skill get(long id) {
        List<Skill> skillsRepository = new ArrayList<>(getAll());
        for (Skill i : skillsRepository) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public Skill update(Skill skill) {
        List<Skill> skillsRepository = new ArrayList<>(getAll());
        for (Skill i : skillsRepository) {
            if (i.getId() == skill.getId()) {
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

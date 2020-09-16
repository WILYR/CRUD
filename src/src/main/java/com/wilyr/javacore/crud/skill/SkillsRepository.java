package src.main.java.com.wilyr.javacore.crud.skill;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SkillsRepository implements ISkillsRepository {
    File file = new File("skills.txt");

    private List<Skill> getAll() {
        List<Skill> currentSkills = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            if (file.length() != 0) {
                String line = reader.readLine();
                while (line != null) {
                    String[] lineSplit = line.split(",");
                    Skill skill = new Skill(lineSplit[1]);
                    skill.setId(Long.parseLong(lineSplit[0]));
                    currentSkills.add(skill);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return currentSkills;
    }

    private long maxId() {
        List<Long> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
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
        List<Skill> currentSkills = new ArrayList<>(getAll());
        boolean isSkillSave = true;
        for (Skill i : currentSkills) {
            if (i.getName().equals(skill.getName())) {
                //skill.setId(i.getId());
                isSkillSave = false;
                break;
            }
        }
        if (isSkillSave) {
            skill.setId(maxId());
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(skill.getId() + "," + skill.getName() + "\n");
            } catch (IOException e) {
                e.getMessage();
            }
        }
        return skill;
    }

    public void delete(Skill skill) {
        List<Skill> currentSkills = new ArrayList<>(getAll());
        Skill removeElement = null;
        for (Skill j : currentSkills) {
            if (j.getName().equals(skill.getName())) {
                removeElement = j;
            }
        }
        if (removeElement == null) {
            System.out.println("Skill " + skill.getName() + " isn't exist");
        } else {
            currentSkills.remove(removeElement);
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Skill i : currentSkills) {
                writer.write(i.getId() + "," + i.getName() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public Skill get(long id) {
        List<Skill> currentSkills = new ArrayList<>(getAll());
        for (Skill i : currentSkills) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }

    public Skill update(Skill skill) {
        List<Skill> currentSkills = new ArrayList<>(getAll());
        for (Skill i : currentSkills) {
            if (i.getId() == skill.getId()) {
                i.setName(skill.getName());
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            for (Skill i : currentSkills) {
                writer.write(i.getId() + "," + i.getName() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return skill;
    }
}

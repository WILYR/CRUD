package com.wilyr.crud.repository.json;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.wilyr.crud.model.Skill;
import com.wilyr.crud.repository.ISkillsRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonSkillsRepository implements ISkillsRepository {
    File fileAccounts = new File("src/main/resources/json/skills.json");

    private List<Skill> getAll() {
        List<Skill> currentAccount = new ArrayList<>();
        try (JsonReader reader = new JsonReader(new BufferedReader(new FileReader(fileAccounts)))) {
            reader.setLenient(true);
            if (fileAccounts.length() != 0) {
                long id = -1;
                String nameSkill = null;
                reader.beginArray();
                while (reader.hasNext()) {
                    reader.beginObject();
                    while (reader.hasNext()) {
                        String name = reader.nextName();
                        if (name.equals("id")) {
                            id = reader.nextLong();
                        } else if (name.equals("name")) {
                            nameSkill = reader.nextString();
                        } else {
                            reader.skipValue();
                        }
                    }
                    reader.endObject();
                    Skill skill = new Skill(nameSkill);
                    skill.setId(id);
                    currentAccount.add(skill);
                }
                reader.endArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentAccount;
    }

    @Override
    public Skill save(Skill skill) {
        List<Skill> skillList = new ArrayList<>(getAll());
        boolean isAccountSave = true;
        for (Skill i : skillList) {
            if (i.getName().equals(skill.getName())) {
                isAccountSave = false;
                break;
            }
        }
        if (isAccountSave) {
            long lastId = 0;
            for (Skill skl : skillList) {
                lastId = skl.getId();
            }
            if (lastId == 0) {
                skill.setId(1);
            } else {
                skill.setId(lastId + 1);
            }
            skillList.add(skill);
            rewrite(skillList);
        }
        return skill;
    }

    private void rewrite(List<Skill> skillList) {
        try (JsonWriter writer = new JsonWriter(new BufferedWriter(new FileWriter(fileAccounts, false)))) {
            writer.beginArray();
            for (Skill skl : skillList) {
                writer.beginObject();
                writer.name("id").value(skl.getId());
                writer.name("name").value(skl.getName());
                writer.endObject();
            }
            writer.endArray();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Override
    public Skill update(Skill skill) {
        List<Skill> skillList = new ArrayList<>(getAll());
        for (Skill i : skillList) {
            if (i.getId() == skill.getId()) {
                i.setName(skill.getName());
            }
        }
        rewrite(skillList);
        return skill;
    }

    @Override
    public void delete(Skill skill) {
        List<Skill> skillList = new ArrayList<>(getAll());
        Skill removeElement = null;
        for (Skill j : skillList) {
            if (j.getName().equals(skill.getName())) {
                removeElement = j;
            }
        }
        if (removeElement == null) {
            System.out.println("Skill " + skill.getName() + " isn't exist");
        } else {
            skillList.remove(removeElement);
            rewrite(skillList);
        }
    }

    @Override
    public Skill get(long id) {
        List<Skill> currentSkills = new ArrayList<>(getAll());
        for (Skill i : currentSkills) {
            if (i.getId() == id) {
                return i;
            }
        }
        return null;
    }
}

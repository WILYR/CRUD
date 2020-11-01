package com.wilyr.crud.repository.json;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.wilyr.crud.model.Account;
import com.wilyr.crud.model.AccountStatus;
import com.wilyr.crud.model.Developer;
import com.wilyr.crud.model.Skill;
import com.wilyr.crud.repository.IDeveloperRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonDeveloperRepository implements IDeveloperRepository {

    File fileAccounts = new File("src/main/resources/json/developers.json");

    public List<Developer> getAll() {
        List<Developer> currentDevelopers = new ArrayList<>();
        try (JsonReader reader = new JsonReader(new BufferedReader(new FileReader(fileAccounts)))) {
            reader.setLenient(true);
            if (fileAccounts.length() != 0) {
                String account = null, skills = null;
                List<Skill> skillList = null;
                reader.beginArray();
                while (reader.hasNext()) {
                    String login = null, password = null;
                    AccountStatus status = null;
                    reader.beginObject();
                    while (reader.hasNext()) {
                        String name = reader.nextName();
                        if (name.equals("account")) {
                            reader.beginObject();
                            while (reader.hasNext()) {
                                String nameAcc = reader.nextName();
                                if (nameAcc.equals("login")) {
                                    login = reader.nextString();
                                } else if (nameAcc.equals("password")) {
                                    password = reader.nextString();
                                } else if (nameAcc.equals("status")) {
                                    String str = reader.nextString();
                                    if (str.equals("ACTIVE")) {
                                        status = AccountStatus.ACTIVE;
                                    }
                                    if (str.equals("BANNED")) {
                                        status = AccountStatus.BANNED;
                                    }
                                    if (str.equals("DELETED")) {
                                        status = AccountStatus.DELETED;
                                    }
                                } else {
                                    reader.skipValue();
                                }
                            }
                            reader.endObject();
                        } else if (name.equals("skills")) {
                            skillList = new ArrayList<>();
                            long id = -1;
                            String nameSkill = null;
                            reader.beginArray();

                            while (reader.hasNext()) {
                                reader.beginObject();
                                while (reader.hasNext()) {
                                    String nameSkl = reader.nextName();
                                    if (nameSkl.equals("id")) {
                                        id = reader.nextLong();
                                    } else if (nameSkl.equals("name")) {
                                        nameSkill = reader.nextString();
                                    } else {
                                        reader.skipValue();
                                    }
                                }
                                reader.endObject();
                                Skill skill = new Skill(nameSkill);
                                skill.setId(id);
                                skillList.add(skill);
                            }
                            reader.endArray();
                        } else {
                            reader.skipValue();
                        }
                    }
                    reader.endObject();
                    Account addAcc = new Account(login, password);
                    addAcc.setAccountStatus(status);
                    currentDevelopers.add(new Developer(skillList, addAcc));
                }
                reader.endArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentDevelopers;
    }

    @Override
    public Developer save(Developer developer) {
        List<Developer> developerList = new ArrayList<>(getAll());
        boolean isAccountSave = true;
        for (Developer i : developerList) {
            if (i.getAccount().getLogin().equals(developer.getAccount().getLogin())) {
                isAccountSave = false;
                break;
            }
        }
        if (isAccountSave) {
            developerList.add(developer);
            rewrite(developerList);
        }
        return developer;
    }

    private void rewrite(List<Developer> developerList) {
        try (JsonWriter writer = new JsonWriter(new BufferedWriter(new FileWriter(fileAccounts, false)))) {
            writer.beginArray();
            for (Developer developer : developerList) {
                writer.beginObject();
                writer.name("account");
                writer.beginObject();
                writer.name("login").value(developer.getAccount().getLogin());
                writer.name("password").value(developer.getAccount().getPassword());
                writer.name("status").value(developer.getAccount().getAccountStatus().toString());
                writer.endObject();
                writer.name("skills");
                writer.beginArray();
                for (Skill skill : developer.getSkills()) {
                    writer.beginObject();
                    writer.name("id").value(skill.getId());
                    writer.name("name").value(skill.getName());
                    writer.endObject();
                }
                writer.endArray();
                writer.endObject();
            }
            writer.endArray();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    @Override
    public void delete(Developer developer) {
        List<Developer> developerList = new ArrayList<>(getAll());
        Developer removeElement = null;
        for (Developer j : developerList) {
            if (j.getAccount().getLogin().equals(developer.getAccount().getLogin())) {
                removeElement = j;
            }
        }
        if (removeElement == null) {
            System.out.println("Developer " + developer.getAccount().getLogin() + " isn't exist");
        } else {
            developerList.remove(removeElement);
            rewrite(developerList);
        }
    }

    @Override
    public Developer get(String login) {
        List<Developer> developerList = new ArrayList<>(getAll());
        for (Developer i : developerList) {
            if (i.getAccount().getLogin().equals(i.getAccount().getLogin())) {
                return i;
            }
        }
        return null;
    }

    @Override
    public Developer update(Developer developer, List<Skill> newSkills) {
        List<Developer> developerList = new ArrayList<>(getAll());
        for (Developer i : developerList) {
            if (i.getAccount().getLogin().equals(developer.getAccount().getLogin())) {
                i.setSkills(newSkills);
            }
        }
        rewrite(developerList);
        return developer;
    }
}

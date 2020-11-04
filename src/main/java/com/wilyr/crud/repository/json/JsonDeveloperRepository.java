package com.wilyr.crud.repository.json;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import com.wilyr.crud.model.Account;
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
                List<Skill> skillList = null;
                long id = -1;
                reader.beginArray();
                while (reader.hasNext()) {
                    reader.beginObject();
                    while (reader.hasNext()) {
                        String name = reader.nextName();
                        if (name.equals("AccountID")) {
                            id = reader.nextLong();
                        } else if (name.equals("skills")) {
                            long skillID = 0;
                            reader.beginArray();
                            skillList = new ArrayList<>();
                            while (reader.hasNext()) {
                                reader.beginObject();
                                while (reader.hasNext()) {
                                    String skillname = reader.nextName();
                                    if (skillname.equals("id")) {
                                        skillID = reader.nextLong();
                                    } else {
                                        reader.skipValue();
                                    }
                                }
                                reader.endObject();
                                JsonSkillsRepository jsonSkillsRepository = new JsonSkillsRepository();
                                Skill skill = jsonSkillsRepository.get(skillID);
                                skillList.add(skill);
                            }

                            reader.endArray();
                        } else {
                            reader.skipValue();
                        }
                    }
                    reader.endObject();
                    Account account = getAccountByID(id);
                    currentDevelopers.add(new Developer(skillList, account));
                }
                reader.endArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return currentDevelopers;
    }

    private Account getAccountByID(long id) {
        Account account = null;
        JsonAccountRepository jsonAccountRepository = new JsonAccountRepository();
        List<Account> accountList = jsonAccountRepository.getAll();
        for (Account acc : accountList) {
            if (acc.getId() == id) {
                account = acc;
            }
        }
        return account;
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
                writer.name("AccountID").value(developer.getAccount().getId());
                writer.name("skills");
                writer.beginArray();
                for (Skill skill : developer.getSkills()) {
                    writer.beginObject();
                    writer.name("id").value(skill.getId());
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

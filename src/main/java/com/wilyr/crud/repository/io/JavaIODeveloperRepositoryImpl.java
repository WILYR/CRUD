package com.wilyr.crud.repository.io;

import com.wilyr.crud.model.Account;
import com.wilyr.crud.model.AccountStatus;
import com.wilyr.crud.model.Developer;
import com.wilyr.crud.model.Skill;
import com.wilyr.crud.repository.IDeveloperRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JavaIODeveloperRepositoryImpl implements IDeveloperRepository {
    File file = new File("src/main/resources/txt/developers.txt");

    private List<Developer> getAll() {
        List<Developer> currentDevelopers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            if (file.length() != 0) {
                String line = reader.readLine();
                while (line != null) {
                    String[] lineSplit = line.split(",");
                    Account account = new Account(lineSplit[0], lineSplit[1]);
                    if (lineSplit[2].equals("ACTIVE")) {
                        account.setAccountStatus(AccountStatus.ACTIVE);
                    } else if (lineSplit[2].equals("DELETED")) {
                        account.setAccountStatus(AccountStatus.DELETED);
                    } else if (lineSplit[2].equals("BANNED")) {
                        account.setAccountStatus(AccountStatus.BANNED);
                    }
                    List<Skill> skills = new ArrayList<>();
                    for (int i = 3; i < lineSplit.length - 1; i = i + 2) {
                        Skill skill = new Skill(lineSplit[i + 1]);
                        skill.setId(Long.parseLong(lineSplit[i]));
                        skills.add(skill);
                    }
                    Developer developer = new Developer(skills, account);
                    currentDevelopers.add(developer);
                    line = reader.readLine();
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return currentDevelopers;
    }

    public Developer save(Developer developer) {
        List<Developer> developerList = new ArrayList<>(getAll());
        boolean isDeveloperSave = true;
        for (Developer i : developerList) {
            if (i.getAccount().getLogin().equals(developer.getAccount().getLogin())) {
                isDeveloperSave = false;
            }
        }
        if (isDeveloperSave) {
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(developer.getAccount().getLogin() + "," + developer.getAccount().getPassword()
                        + "," + developer.getAccount().getAccountStatus());
                List<Skill> skills = developer.getSkills();
                for (Skill i : skills) {
                    writer.write("," + i.getId() + "," + i.getName());
                }
                writer.write("\n");
            } catch (IOException e) {
                e.getMessage();
            }
        }
        return developer;
    }

    public void delete(Developer developer) {
        List<Developer> developerList = new ArrayList<>(getAll());
        Developer removeDeveloper = null;
        for (Developer i : developerList) {
            if (i.getAccount().getLogin().equals(developer.getAccount().getLogin())) {
                removeDeveloper = i;
            }
        }
        if (removeDeveloper == null) {
            System.out.println("Developer isn't Exist");
        } else {
            developerList.remove(removeDeveloper);
            rewrite(developerList);
        }
    }

    private void rewrite(List<Developer> developerList) {
        try (FileWriter writer = new FileWriter(file)) {
            for (Developer developer : developerList) {
                writer.write(developer.getAccount().getLogin() + "," + developer.getAccount().getPassword()
                        + "," + developer.getAccount().getAccountStatus());
                List<Skill> skills = developer.getSkills();
                for (Skill i : skills) {
                    writer.write("," + i.getId() + "," + i.getName());
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public Developer get(String login) {
        List<Developer> developerList = new ArrayList<>(getAll());
        for (Developer i : developerList) {
            if (i.getAccount().getLogin().equals(login)) {
                return i;
            }
        }
        return null;
    }

    public Developer update(Developer developer, List<Skill> newSkills) {
        List<Developer> developerList = new ArrayList<>(getAll());
        for (Developer i : developerList) {
            if (i.getAccount().getLogin().equals(developer.getAccount().getLogin())) {
                i.setSkills(newSkills);
            } else {
                System.out.println("Developer not exist");
            }
        }
        rewrite(developerList);
        return developer;
    }
}

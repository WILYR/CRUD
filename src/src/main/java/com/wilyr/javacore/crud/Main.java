package src.main.java.com.wilyr.javacore.crud;

import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        Skills skill1 = new Skills("Maven", 1);
        Skills skill2 = new Skills("Core", 2);
        Skills skill3 = new Skills("SQL", 3);

        add(skill1);
        add(skill2);
        add(skill3);
        getAll();
        delete("Core");
        get("Maven");

        update(skill1, "Gradle");
    }

    public static void add(Skills skill) {
        try (FileWriter writer = new FileWriter("skills.txt", true)) {
            writer.write(skill.getId() + ", " + skill.getName() + "\n");
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void delete(String remove) {
        File oldFile = new File("skills.txt");

        try {
            FileReader fr = new FileReader(oldFile);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> list = new ArrayList<>();
            String line = br.readLine();
            while (line != null) {
                if (!line.contains(remove))
                    list.add(line);
                line = br.readLine();
            }
            fr.close();
            br.close();
            FileWriter fw = new FileWriter("skills.txt");
            for (String i : list) {
                fw.write(i + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void getAll() {
        try (FileReader reader = new FileReader("skills.txt")) {
            int i;
            while ((i = reader.read()) != -1) {
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static void get(String get) {
        try (BufferedReader reader = new BufferedReader(new FileReader("skills.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                if (line.contains(get))
                    System.out.println(line);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
    public static void update(Skills skill, String newName) {
        delete(skill.getName());
        Skills newSkill = new Skills(newName, skill.getId());
        add(newSkill);
    }
}

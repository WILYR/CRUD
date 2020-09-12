package src.main.java.com.wilyr.javacore.crud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Skills {
    private String name;
    private int id;

    public HashMap<Integer, String> getAll() {
        HashMap<Integer, String> hashMap = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("skills.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                String[] lineSplit = line.split(",");
                hashMap.put(Integer.parseInt(lineSplit[0]), lineSplit[1]);
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.getMessage();
        }
        return hashMap;
    }

    public void add(int id, String name) {
        HashMap<Integer, String> hashMap = getAll();
        if(!hashMap.containsKey(id)) {
            try (FileWriter writer = new FileWriter("skills.txt", true)) {
                writer.write(id + "," + name + "\n");
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    public void delete (Integer id){
        HashMap<Integer, String> hashMap = getAll();
        hashMap.remove(id);
        try (FileWriter writer = new FileWriter("skills.txt")) {
            Set<Map.Entry<Integer, String>> set= hashMap.entrySet();
            for(Map.Entry<Integer, String> i: set) {
                writer.write(i.getKey() + "," + i.getValue() + "\n");
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public String get(int id){
        HashMap<Integer, String> hashMap = getAll();
        return hashMap.get(id);
    }

    public void update(int id, String name){
        delete(id);
        add(id, name);
    }

}

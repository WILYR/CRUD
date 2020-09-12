package src.main.java.com.wilyr.javacore.crud;

public class Main {

    public static void main(String[] args) {
        Skills skill = new Skills();
        skill.add(1, "Maven");
        skill.add(2, "Core");
        skill.delete(1);
        skill.add(1, "Maven");
        skill.get(1);
        skill.update(1, "Gradle");
    }
}

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    // Define enums for clarity
    enum Haus {Stark, Lannister, Targaryen, Baratheon, Greyjoy}

    // Define a class to represent tasks
    static class Task {
        int date;
        int id;
        String mitgliedsName;
        //Group group;
        String haus;
        //Difficulty difficulty;
        //Date date;
        String ereignis;

        public Task(String line) {
            String[] fields = line.split("<");
            this.date = Integer.parseInt(fields[0]);
            this.ereignis = fields[1];
            this.haus = fields[2];
            this.id = Integer.parseInt(fields[3]);
            this.mitgliedsName = fields[4];

            //this.group = Group.valueOf(fields[2].trim());
            //this.id = Integer.parseInt(fields[4]);

        }

    }

    public static void main(String[] args) throws IOException {
        List<Task> tasks = Files.lines(Paths.get("evenimente.xml"))
                .map(Task::new)
                .toList();
    }

}

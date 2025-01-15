import java.util.*;
import java.util.stream.*;

public class WesterosChronicles {

    // Inner class representing each event
    static class Event {
        int id;
        String mitgliedsname;
        String haus;
        String ereignis;
        String datum; // Date in YYYY-MM-DD format

        public Event() {
        }

        public String getDatum() {
            return datum;
        }

        public String getHaus() {
            return haus;
        }

        public String getMitgliedsname() {
            return mitgliedsname;
        }

        @Override
        public String toString() {
            return datum + ": " + mitgliedsname + " - " + ereignis;
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        String filename = "C:\\Users\\redan\\Desktop\\MAP an 2 sem 1\\MAP examen ex1\\evenimente.xml";

        List<Event> events = readXml(filename);//will add it

        // a)
        System.out.println("Geben Sie einen Großbuchstaben ein, um Mitglieder zu filtern:");
        char filterChar = scanner.nextLine().charAt(0);
        List<String> filteredMembers = events.stream()
                .map(Event::getMitgliedsname)
                .filter(name -> name.startsWith(String.valueOf(filterChar)))
                .distinct()
                .collect(Collectors.toList());
        System.out.println("\nGefilterte Namen:");
        filteredMembers.forEach(System.out::println);
    }




    //Files.write(Paths.get("result.txt"), results);

}

//        // Task b: Display
//        System.out.println("Space Group Tasks by Difficulty:");
//        tasks.stream()
//                .filter(task -> task.group == Group.Space)
//                .sorted(Comparator.comparing(task -> task.difficulty))
//                .map(task -> task.taskName)
//                .forEach(System.out::println);
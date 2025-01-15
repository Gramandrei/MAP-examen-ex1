import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
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

        //b)
        System.out.println("\nEreignisse des Hauses Stark nach Datum sortiert:");
        events.stream()
                .filter(event -> event.getHaus().equals("Stark"))
                .sorted(Comparator.comparing(Event::getDatum))
                .forEach(System.out::println);
    }

    private static List<Event> readXml(String filename) throws Exception {
        List<Event> events = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(filename);

        NodeList logNodes = document.getElementsByTagName("log");
        for (int i = 0; i < logNodes.getLength(); i++) {
            var logNode = logNodes.item(i).getChildNodes();
            Event event = new Event();
            for (int j = 0; j < logNode.getLength(); j++) {
                var node = logNode.item(j);
                switch (node.getNodeName()) {
                    case "Id" -> event.id = Integer.parseInt(node.getTextContent());
                    case "Mitgliedsname" -> event.mitgliedsname = node.getTextContent();
                    case "Haus" -> event.haus = node.getTextContent();
                    case "Ereignis" -> event.ereignis = node.getTextContent();
                    case "Datum" -> event.datum = node.getTextContent();
                }
            }
            events.add(event);
        }
        return events;
    }





    //Files.write(Paths.get("result.txt"), results);

}
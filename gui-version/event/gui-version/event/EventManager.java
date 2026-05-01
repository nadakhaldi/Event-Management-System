
package event;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private ArrayList<Event> events = new ArrayList<>();
    private static final DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private static final String file = "Nada.txt";

    public void add(Event event) {
        events.add(event);
    }

    public boolean remove(String name) {
        return events.removeIf(event -> event.getEventName().equalsIgnoreCase(name));
    }

    public void display() {
        if (events.isEmpty()) {
            System.out.println("No events available. Please add events first.");
        } else {
            System.out.println("=== Events List ===");
            for (Event event : events) {
                System.out.println(event);
            }
            System.out.println("==================");
        }
    }

    public List<Event> retrieve(LocalDateTime date, String name) {
        List<Event> result = new ArrayList<>();
        
        // If both parameters are empty/null, return all events
        if ((date == null || date.toString().isEmpty()) && (name == null || name.trim().isEmpty())) {
            return new ArrayList<>(events);
        }
        
        for (Event e : events) {
            boolean matchDate = false;
            boolean matchName = false;
            
            // Check date match
            if (date != null) {
                matchDate = e.getStartDateTime().toLocalDate().equals(date.toLocalDate());
            }
            
            // Check name match
            if (name != null && !name.trim().isEmpty()) {
                matchName = e.getEventName().equalsIgnoreCase(name.trim());
            }
            
            // Add event if it matches criteria
            if ((date == null && name == null) || 
                (date != null && name == null && matchDate) || 
                (date == null && name != null && matchName) || 
                (date != null && name != null && (matchDate || matchName))) {
                result.add(e);
            }
        }
        return result;
    }
    
    public List<Event> getAllEvents() {
        return new ArrayList<>(events);
    }
    public void save() {
        try (BufferedWriter write = new BufferedWriter(new FileWriter(file))) {
            for (Event e : events) {
                write.write(e.getEventName() + "|" + e.getStartDateTime().format(form) + "|"
                        + e.getEndDateTime().format(form));
                write.newLine();
            }
            System.out.println("Events saved successfully to " + file);
        } catch (IOException e) {
            System.out.println("Error saving events: " + e.getMessage());
        }
    }
    public List<Event> getAll() {
        return events;
    }
    public void load() {
        events.clear();
        try (BufferedReader read = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = read.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 3) {
                    String eventName = parts[0].trim();
                    LocalDateTime startDateTime = LocalDateTime.parse(parts[1].trim(), form);
                    LocalDateTime endDateTime = LocalDateTime.parse(parts[2].trim(), form);

                    try {
                        Event event = new Event(eventName, startDateTime, endDateTime);
                        events.add(event);
                    } catch (InvalidEventNameException | InvalidEventDateException e) {
                        System.out.println("Skipping invalid event: " + eventName);
                    }
                }
            }
            System.out.println("Events loaded from " + file);
        } catch (FileNotFoundException e) {
            System.out.println("No existing events file found - starting fresh");
        } catch (IOException e) {
            System.out.println("Error loading events: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }
    }
}

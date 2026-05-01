package event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MainEvent2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EventManager eventManager = new EventManager();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        eventManager.load();
        eventManager.display();

        while (true) {
            System.out.println("Enter your choice:");
            System.out.println("1: Add an event");
            System.out.println("2: Remove an event");
            System.out.println("3: Display the events");
            System.out.println("4: Retrieve events by name or date");
            System.out.println("5: Exit the program");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter event name: ");
                        String eventName = scanner.nextLine();

                        System.out.print("Enter start date (dd/MM/yyyy HH:mm): ");
                        String startInput = scanner.nextLine();
                        LocalDateTime startDateTime = LocalDateTime.parse(startInput, formatter);

                        System.out.print("Enter end date (dd/MM/yyyy HH:mm): ");
                        String endInput = scanner.nextLine();
                        LocalDateTime endDateTime = LocalDateTime.parse(endInput, formatter);

                        Event event = new Event(eventName, startDateTime, endDateTime);
                        eventManager.add(event);
                        eventManager.save(); // Save the event
                        System.out.println("Event added");
                    } catch (InvalidEventNameException | InvalidEventDateException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Other error: " + e.getMessage());
                    }
                    break;

                case 2:
                    System.out.print("Enter the event name to remove: ");
                    String remove = scanner.nextLine();
                    if (eventManager.remove(remove)) {
                        eventManager.save(); // Save after removing
                        System.out.println("Event removed");
                    } else {
                        System.out.println("Can't find event");
                    }
                    break;

                case 3:
                    eventManager.display();
                    break;

                case 4:
                    System.out.print("Enter the event name to search (or press Enter to skip): ");
                    String name = scanner.nextLine().trim();

                    System.out.print("Enter the event date (dd/MM/yyyy HH:mm) or press Enter to skip: ");
                    String dateInput = scanner.nextLine().trim();
                    LocalDateTime date = null;

                    if (!dateInput.isEmpty()) {
                        try {
                            date = LocalDateTime.parse(dateInput, formatter);
                        } catch (Exception e) {
                            System.out.println("Invalid date format. Please use dd/MM/yyyy HH:mm");
                            break;
                        }
                    }

                    List<Event> foundEvents = eventManager.retrieve(date, name);
                    if (foundEvents.isEmpty()) {
                        System.out.println("No events found.");
                    } else {
                        System.out.println("Matching events:");
                        for (Event e : foundEvents) {
                            System.out.println(e);
                        }
                    }
                    break;

                case 5:
                    eventManager.save();
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Choose again.");
                    break;
            }
        }
    }
}


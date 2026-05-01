package event;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class MainEvent {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		DateTimeFormatter form = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		try {
			System.out.print("Enter event name: ");
			String eventName = scanner.nextLine();

			System.out.print("Enter start date and time (dd/MM/yyyy HH:mm): ");
			String start = scanner.nextLine();
			LocalDateTime startDateTime = LocalDateTime.parse(start, form);

			System.out.print("Enter end date and time (dd/MM/yyyy HH:mm): ");
			String end = scanner.nextLine();
			LocalDateTime endDateTime = LocalDateTime.parse(end, form);

			Event event = new Event(eventName, startDateTime, endDateTime);
			System.out.println("Event created");

			EventManager eventManager = new EventManager();
			eventManager.add(event);
			eventManager.display();

		} catch (InvalidEventNameException | InvalidEventDateException e) {
			System.out.println("Error " + e.getMessage());
		} catch (Exception e) {
			System.out.println("an other error " + e.getMessage());
		} finally {
			scanner.close();
		}
	}

}

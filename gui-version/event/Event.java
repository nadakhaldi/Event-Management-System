package event;

import java.time.LocalDateTime;
import java.time.Duration;

public class Event {

	private String eventName;
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;

	public Event(String eventName, LocalDateTime startDateTime, LocalDateTime endDateTime)
			throws InvalidEventNameException, InvalidEventDateException {

		if (eventName.trim().isEmpty()) {
			throw new InvalidEventNameException("event name can't be empty");
		}

		if (startDateTime.isBefore(LocalDateTime.now())) {
			throw new InvalidEventDateException("event can't be in the past");
		}
		Duration d = Duration.between(startDateTime, endDateTime);

		if (d.isZero()) {
			throw new InvalidEventDateException("there is no duration between two dates you enterd");
		}
		if (d.isNegative()) {
			throw new InvalidEventDateException("start date can't be after end date");
		}

		this.eventName = eventName;
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
	}

	

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public LocalDateTime getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(LocalDateTime startDateTime) {
		this.startDateTime = startDateTime;
	}

	public LocalDateTime getEndDateTime() {
		return endDateTime;
	}

	public void setEndDateTime(LocalDateTime endDateTime) {
		this.endDateTime = endDateTime;
	}

	@Override
	public String toString() {
		return "Event  " + eventName + " | Starts in " + startDateTime + " | ends in  " + endDateTime;
	}
}

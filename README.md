# Event-Management-System
Event Management System – Console &amp; GUI Version
---

#  Event Management System (CLI & GUI)

This project is an **Event Management System** developed in Java as part of practical work (TP) in OOP .

It provides two implementations:

*  **Console-based (CLI) version**
*  **Graphical User Interface (GUI) version using Swing**

---

##  Overview

The system allows users to manage events by:

* Creating events with a name, start date, and end date
* Validating event data (name & date constraints)
* Storing events in a file
* Searching and retrieving events
* Deleting events

Both versions share the same core logic (`Event`, `EventManager`, exceptions), ensuring consistency between CLI and GUI.



##  Features

### Event Validation

* Event name cannot be empty
* Event cannot be in the past
* Start date must be before end date
* Event must have a valid duration

### File Persistence

* Events are saved in a file: `Nada.txt`
* Automatic loading on startup
* Data stored in format:

```
EventName | StartDate | EndDate


##  Console Version (CLI)

### Features:

* Add event
* Remove event
* Display all events
* Retrieve events by:

  * Name
  * Date
* Auto-save after operations



## GUI Version (Swing)

### Features:

* Modern interface using **Java Swing**
* Tab-based navigation:

  * 📊 Dashboard → View all events
  * ➕ Add Event → Create new event
  * 🔍 Search Events → Filter by name/date
  * ⚙️ Manage Events → Delete events
* Interactive components:

  * Tables (JTable)
  * Forms (JTextField)
  * Buttons with custom icons
  * Dialog messages (JOptionPane)


## Technologies Used

* **Java**
* **Java Swing (GUI)**
* `java.time` (LocalDateTime, Duration)
* File handling (BufferedReader / BufferedWriter)


##  Notes

* Both versions use the same data file (`Nada.txt`)
* GUI version extends CLI functionality with better usability
* Code includes exception handling for robustness
* Designed for educational purposes (TP)




نقدر نرتبلك repo كامل step by step 👌

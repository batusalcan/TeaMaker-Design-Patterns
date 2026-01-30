# ☕ TeaMaker - Design Patterns Simulation

> **A Java Swing application demonstrating Software Design Patterns.**
> *Developed for SE 3317 - Software Design and Architecture Course.*

## 🎓 Course Information

* **Course:** SE 3317 - Software Design and Architecture
* **Instructor:** Dr. Deniz Özsoyeller
* **Institution:** Yaşar University
* **Language:** Java

---

## 📖 Project Overview

**TeaMaker** is a simulation software that models the process of brewing tea using a smart kettle system. The primary goal of this project is to implement and demonstrate various **Object-Oriented Design Patterns** to solve architectural problems such as state management, algorithm selection, and user interface updates.

---

## 🛠️ Design Patterns Implemented

This project strictly follows the **MVC (Model-View-Controller)** architectural pattern and incorporates the following design patterns:

### 1. State Pattern 🔄
* **Usage:** Manages the internal state of the Tea Maker (e.g., Idle, Boiling, Brewing, Done).
* **Location:** `src/model/states`
* **Benefit:** Eliminates complex `if-else` or `switch` statements by encapsulating state-specific behaviors in separate classes (`BoilingWaterState`, `IdleState`, etc.).

### 2. Strategy Pattern 🧠
* **Usage:** Defines a family of algorithms for processing water and tea (e.g., `WaterBoilingStrategy`, `TeaBrewingStrategy`).
* **Location:** `src/model/strategies`
* **Benefit:** Allows the brewing algorithms to be selected or switched at runtime without altering the context.

### 3. Observer Pattern 👀
* **Usage:** Establishes a subscription mechanism to notify the User Interface (View) whenever the Tea Maker's status changes.
* **Location:** `src/model/observer`
* **Benefit:** Decouples the UI from the logic; the View updates automatically when the Model changes state.

### 4. Decorator Pattern 🎀
* **Usage:** Dynamically adds responsibilities to status messages, such as adding "Health Warnings" to standard messages.
* **Location:** `src/model/decorator`
* **Benefit:** Provides a flexible alternative to subclassing for extending functionality (e.g., `HealthWarningDecorator`).

### 5. Singleton Pattern 🔒
* **Usage:** Ensures that the `DatabaseManager` class has only one instance globally.
* **Location:** `src/model/DatabaseManager.java`
* **Benefit:** Provides a centralized point of access to the database connection.

---

## 🏗️ Architecture (MVC)

* **Model:** Handles data, logic, and state (`TeaMakerModel`, `DatabaseManager`).
* **View:** Displays the GUI to the user (`TeaMakerView`).
* **Controller:** Handles user input and updates the model (`TeaMakerController`).

---

## 💻 Tech Stack

* **Language:** Java 17+
* **GUI:** Java Swing
* **Database:** MySQL (via `DatabaseManager`)

---

## 🚀 How to Run

1.  Clone the repository.
2.  Import the `src` folder into your favorite IDE (IntelliJ IDEA, Eclipse, or NetBeans).
3.  Ensure MySQL is running and update credentials in `DatabaseManager.java`.
4.  Run `TeaMakerTestDrive.java` to start the application.

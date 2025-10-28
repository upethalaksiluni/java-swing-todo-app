# ✅ Java Swing To-Do Application

A simple and user-friendly **desktop To-Do list application** built using the **Java Swing GUI framework**.

---

## ✨ Features

- ➕ Add new tasks
- ✅ Mark tasks as complete / incomplete
- 🗑️ Delete selected tasks
- 🧹 Clear all tasks at once
- 🔢 Displays total task count
- 🛡️ Input validation (prevents empty tasks)
- 🎨 Clean and intuitive GUI

---

## 🛠️ Technologies Used

| Technology | Purpose |
|-----------|---------|
| **Java** (JDK 8+) | Core programming language |
| **Swing** | GUI framework |
| IntelliJ IDEA / Eclipse | IDE for development |

---

## 📁 Project Structure

src/
└── main/
  └── java/
    └── todoapplication/
      ├── Main.java → Application Entry Point
      ├── TodoApp.java → GUI Application Window
      ├── Task.java → Task Model Class
      └── TodoModel.java → Business Logic (Task Management)

yaml
Copy code

---

## ▶️ How to Run

1. **Clone** the repository  
2. Open it in **IntelliJ IDEA** 
3. Navigate to **Main.java**
4. Right-click → **Run**
5. The To-Do app window will launch 🎉

---

## 🧑‍💻 Usage Instructions

| Action | What to Do |
|-------|------------|
| Add a task | Enter text → Click **Add Task** or press **Enter** |
| Mark complete | Select task → Click **Mark Complete** |
| Delete task | Select task → Click **Delete** |
| Clear all tasks | Click **Clear All** |

---

## 🧱 Key Concepts Implemented

- ☑ **Event Handling** using `ActionListener`
- 📐 **Layout Managers:** `BorderLayout`, `FlowLayout`
- 🏛 **MVC Architecture** (Model & View separation)
- 🪟 Swing Components: `JFrame`, `JButton`, `JTextField`, `JList`, `JLabel`
- 🧵 Runs GUI updates on **Event Dispatch Thread (EDT)**

---

## 📦 Installation (Clone & Run)

```bash
git clone https://github.com/upethalaksiluni/java-swing-todo-app.git
cd java-swing-todo-app
```

👤 Author
Java Developer Internship – Task 6

📜 License
This project is licensed under the MIT License.

🎯 Interview Questions & Answers
Q1. What is Swing?
Swing is a Java GUI framework built on top of AWT, providing lightweight, platform-independent components. It supports advanced UI controls and customizable look-and-feel.

Q2. Difference between AWT and Swing
Criteria	AWT	Swing
Component Type	Heavyweight	Lightweight
Platform Dependence	Yes (OS-dependent look)	No (consistent UI)
Components	Limited	Rich component set
Customization	Difficult	Easy & flexible
Performance	Faster native rendering	Slightly slower but stable

Q3. What is ActionListener?
ActionListener is an interface that handles action events (like button clicks).
It requires implementing actionPerformed(ActionEvent e).

Example:

java
Copy code
button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        // Action logic
    }
});
Q4. How are layouts managed in Java?
Java provides layout managers such as:

BorderLayout

FlowLayout

GridLayout

BoxLayout

GridBagLayout

CardLayout

null layout (manual positioning)

Q5. What is the Event Dispatch Thread (EDT)?
The EDT is a special thread responsible for handling all GUI updates and events.
Swing components must be accessed only from the EDT to avoid thread conflicts.

Q6. Common Swing GUI Components
JFrame, JPanel, JButton, JLabel, JTextField, JList, JComboBox, JCheckBox, JTable, JTree, JScrollPane, etc.

Q7. How to handle multiple events?
Use one listener for many components

Use separate listeners

Check getSource() in actionPerformed()

Use anonymous inner classes

Example:

java
Copy code
if (e.getSource() == button1) {...}
Q8. Difference between JFrame and JPanel
Component	Description
JFrame	Top-level application window
JPanel	Container used to group components

Q9. How to add a Scroll Bar?
Wrap the component inside JScrollPane.

java
Copy code
JScrollPane scrollPane = new JScrollPane(list);
panel.add(scrollPane);
Q10. What is MVC Architecture?
Layer	Responsibility
Model	Data & logic (Task, TodoModel)
View	UI (TodoApp)
Controller	User interaction handling

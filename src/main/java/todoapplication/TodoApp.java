package todoapplication;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoApp extends JFrame {
    private TodoModel model;
    private JTextField taskInputField;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton completeButton;
    private JButton clearAllButton;
    private JLabel taskCountLabel;
    private int selectedIndex = -1;

    public TodoApp() {
        model = new TodoModel();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("To-Do Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setResizable(true);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel topPanel = createTopPanel();
        JPanel centerPanel = createCenterPanel();
        JPanel bottomPanel = createBottomPanel();

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }

    private JPanel createTopPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 0));

        JLabel label = new JLabel("Enter Task:");
        label.setFont(new Font("Arial", Font.BOLD, 12));

        taskInputField = new JTextField();
        taskInputField.setFont(new Font("Arial", Font.PLAIN, 12));
        taskInputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        addButton = new JButton("Add Task");
        addButton.setFont(new Font("Arial", Font.BOLD, 12));
        addButton.setBackground(new Color(52, 152, 219));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTask();
            }
        });

        panel.add(label, BorderLayout.WEST);
        panel.add(taskInputField, BorderLayout.CENTER);
        panel.add(addButton, BorderLayout.EAST);

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 10));

        JLabel listLabel = new JLabel("Tasks:");
        listLabel.setFont(new Font("Arial", Font.BOLD, 12));

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 12));
        taskList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        taskList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                selectedIndex = taskList.getSelectedIndex();
            }
        });

        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setPreferredSize(new Dimension(550, 250));

        panel.add(listLabel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0));

        deleteButton = new JButton("Delete");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 11));
        deleteButton.setBackground(new Color(231, 76, 60));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteTask();
            }
        });

        completeButton = new JButton("Mark Complete");
        completeButton.setFont(new Font("Arial", Font.BOLD, 11));
        completeButton.setBackground(new Color(46, 204, 113));
        completeButton.setForeground(Color.WHITE);
        completeButton.setFocusPainted(false);
        completeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                completeTask();
            }
        });

        clearAllButton = new JButton("Clear All");
        clearAllButton.setFont(new Font("Arial", Font.BOLD, 11));
        clearAllButton.setBackground(new Color(155, 89, 182));
        clearAllButton.setForeground(Color.WHITE);
        clearAllButton.setFocusPainted(false);
        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearAllTasks();
            }
        });

        buttonPanel.add(deleteButton);
        buttonPanel.add(completeButton);
        buttonPanel.add(clearAllButton);

        JPanel statusPanel = new JPanel();
        statusPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        taskCountLabel = new JLabel("Tasks: 0");
        taskCountLabel.setFont(new Font("Arial", Font.BOLD, 11));
        statusPanel.add(taskCountLabel);

        panel.add(buttonPanel, BorderLayout.WEST);
        panel.add(statusPanel, BorderLayout.EAST);

        return panel;
    }

    private void addTask() {
        String taskTitle = taskInputField.getText().trim();
        if (taskTitle.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a task!", "Empty Task", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Task task = new Task(taskTitle);
        model.addTask(task);
        listModel.addElement(task.toString());
        taskInputField.setText("");
        updateTaskCount();
    }

    private void deleteTask() {
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a task to delete!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Delete this task?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            model.deleteTask(selectedIndex);
            listModel.remove(selectedIndex);
            updateTaskCount();
            selectedIndex = -1;
        }
    }

    private void completeTask() {
        if (selectedIndex < 0) {
            JOptionPane.showMessageDialog(this, "Please select a task to mark complete!", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        model.toggleTaskCompletion(selectedIndex);
        Task task = model.getTask(selectedIndex);
        listModel.set(selectedIndex, task.toString());
        updateTaskCount();
    }

    private void clearAllTasks() {
        if (model.getTaskCount() == 0) {
            JOptionPane.showMessageDialog(this, "No tasks to clear!", "Empty List", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Clear all tasks?", "Confirm Clear", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            listModel.clear();
            model = new TodoModel();
            updateTaskCount();
            selectedIndex = -1;
        }
    }

    private void updateTaskCount() {
        taskCountLabel.setText("Tasks: " + model.getTaskCount());
    }
}
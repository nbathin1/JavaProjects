package com.application.Remainderjava;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

public class RemainderManager extends JFrame {
    private static final long serialVersionUID = 1L;
	private JTextField titleField;
    private JTextField timeField;
    private JTextArea resultArea;
    private JButton createButton;
    private JButton viewButton;
    private JButton markDoneButton;
    private JButton deleteButton;
    private JButton findButton;
    private JButton exitButton;

    private RemainderDao remainderDao;
    private User user;
    @SuppressWarnings("unused")
	private Remainder remainder;

    public RemainderManager(User user) {
        this.user = user;
        this.remainderDao = new RemainderDao();

        setTitle("Remainder Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel titleLabel = new JLabel("Remainder Title:");
        titleLabel.setBounds(30, 30, 120, 25);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(150, 30, 200, 25);
        add(titleField);

        JLabel timeLabel = new JLabel("Time (yyyy-MM-dd HH:mm):");
        timeLabel.setBounds(30, 70, 200, 25);
        add(timeLabel);

        timeField = new JTextField();
        timeField.setBounds(150, 70, 200, 25);
        add(timeField);

        createButton = new JButton("Create Remainder");
        createButton.setBounds(30, 110, 150, 30);
        add(createButton);

        viewButton = new JButton("View All");
        viewButton.setBounds(190, 110, 100, 30);
        add(viewButton);

        markDoneButton = new JButton("Mark as Done");
        markDoneButton.setBounds(30, 150, 150, 30);
        add(markDoneButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(190, 150, 100, 30);
        add(deleteButton);

        findButton = new JButton("Find");
        findButton.setBounds(30, 190, 100, 30);
        add(findButton);

        exitButton = new JButton("Exit");
        exitButton.setBounds(190, 190, 100, 30);
        add(exitButton);

        resultArea = new JTextArea();
        resultArea.setBounds(30, 230, 420, 120);
        add(resultArea);

        setVisible(true);

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createRemainder();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewRemainders();
            }
        });

        markDoneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                markRemainderDone();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteRemainder();
            }
        });

        findButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                findRemainder();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void createRemainder() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        String title = titleField.getText();
        String timeText = timeField.getText();

        try {
            LocalDateTime time = LocalDateTime.parse(timeText, formatter);

            Remainder newRemainder = new Remainder();
            newRemainder.setTitle(title);
            newRemainder.setTime(time);
            newRemainder.setUser_id(user.getUser_id());
            newRemainder.setDone(false);

            remainderDao.save(newRemainder);
            resultArea.setText("Remainder created successfully!");

        } catch (Exception e) {
            resultArea.setText("Error: Invalid time format. Use yyyy-MM-dd HH:mm.");
        }
    }

    private void viewRemainders() {
        List<Remainder> remainders = remainderDao.getAll(user.getUser_id());
        if (remainders.isEmpty()) {
            resultArea.setText("No remainders found.");
        } else {
            StringBuilder result = new StringBuilder("Remainders:\n");
            for (Remainder r : remainders) {
                result.append("Title: ").append(r.getTitle())
                      .append(", Time: ").append(r.getTime())
                      .append(", Done: ").append(r.isDone() ? "Yes" : "No")
                      .append("\n");
            }
            resultArea.setText(result.toString());
        }
    }

    private void markRemainderDone() {
        String title = titleField.getText();

        Remainder r = new Remainder();
        r.setTitle(title);
        r.setUser_id(user.getUser_id());

        remainderDao.update(r);
        resultArea.setText("Remainder marked as done.");
    }

    private void deleteRemainder() {
        String title = titleField.getText();

        Remainder r = new Remainder();
        r.setTitle(title);
        r.setUser_id(user.getUser_id());

        remainderDao.delete(r);
        resultArea.setText("Remainder deleted.");
    }

    private void findRemainder() {
        String title = titleField.getText();

        Remainder r = new Remainder();
        r.setTitle(title);
        r.setUser_id(user.getUser_id());

        Optional<Remainder> foundRemainder = remainderDao.find(r);
        if (foundRemainder.isPresent()) {
            resultArea.setText("Remainder found: " + foundRemainder.get().getTitle());
        } else {
            resultArea.setText("Remainder not found.");
        }
    }
}

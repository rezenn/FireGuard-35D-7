package controller;

import dao.ScheduleDAO;
import model.Schedule;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleController {
    private ScheduleDAO scheduleDAO;

    public ScheduleController(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }

    public void addSchedule(String name, String rank, String phone_number,String email, String date, String shift) {
        if (name.isEmpty() || rank.isEmpty() || phone_number.isEmpty() ||email.isEmpty() || date.isEmpty() || shift.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Schedule schedule = new Schedule(name, rank, phone_number, email, date, shift);
                boolean success = scheduleDAO.insertSchedule(schedule);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Schedule added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add schedule. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error occurred. Failed to add schedule: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid age format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<Schedule> getAllSchedules() {
        try {
            return scheduleDAO.getAllSchedules();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to retrieve schedule: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
}

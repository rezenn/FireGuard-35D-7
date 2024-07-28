package controller;

import dao.StaffDAO;
import model.Staff;
import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StaffController {
    private StaffDAO staffDAO;

    public StaffController(StaffDAO staffDAO) {
        this.staffDAO = staffDAO;
    }

    public void addStaff(String name, String staff_id, String rank, String age, String phone_number, String email, String address, String recruit_date) {
        if (name.isEmpty() || staff_id.isEmpty() || rank.isEmpty() || age.isEmpty() || phone_number.isEmpty() || email.isEmpty() || address.isEmpty() || recruit_date.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                int ageInt = Integer.parseInt(age); // Ensure age is a valid integer
                Staff staff = new Staff(name, staff_id, rank, ageInt, phone_number, email, address, recruit_date);
                boolean success = staffDAO.insertStaff(staff);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Staff added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add staff. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error occurred. Failed to add staff: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid age format. Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<Staff> getAllStaffs() {
        try {
            return staffDAO.getAllStaffs();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Failed to retrieve staffs: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
}

package controller;

import dao.OperationDAO;
import model.Operation;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

public class OperationController {
    private OperationDAO operationDAO;

    public OperationController(OperationDAO operationDAO) {
        this.operationDAO = operationDAO;
    }

    public void insertOperation(int incidentId, String nameOfCaller, String typeOfIncident, String cause, int noOfInjured,
                                int noOfCasualties, String timeOfIncident, String dateOfIncident, String placeOfIncident, String damageOfProperty) {
        if (nameOfCaller.isEmpty() || typeOfIncident.isEmpty() || cause.isEmpty() || timeOfIncident.isEmpty() || 
            dateOfIncident.isEmpty() || placeOfIncident.isEmpty() || damageOfProperty.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Operation operation = new Operation(incidentId, nameOfCaller, typeOfIncident, cause, noOfInjured,
                                                    noOfCasualties, timeOfIncident, dateOfIncident, placeOfIncident, damageOfProperty);
                boolean success = operationDAO.insertOperation(operation);

                if (success) {
                    JOptionPane.showMessageDialog(null, "Operation added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add operation. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error occurred. Failed to add operation.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    public void updateOperation(int incidentId, String nameOfCaller, String typeOfIncident, String cause, int noOfInjured,
                                int noOfCasualties, String timeOfIncident, String dateOfIncident, String placeOfIncident, String damageOfProperty) {
        if (nameOfCaller.isEmpty() || typeOfIncident.isEmpty() || cause.isEmpty() || timeOfIncident.isEmpty() || 
            dateOfIncident.isEmpty() || placeOfIncident.isEmpty() || damageOfProperty.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in all required fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Operation operation = new Operation(incidentId, nameOfCaller, typeOfIncident, cause, noOfInjured,
                                                    noOfCasualties, timeOfIncident, dateOfIncident, placeOfIncident, damageOfProperty);
                operationDAO.updateOperation(operation);
                    JOptionPane.showMessageDialog(null, "Operation updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Database error occurred. Failed to update operation.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public List<Operation> getAllOperations() throws SQLException {
        return operationDAO.getAllOperations();
    }

    
}

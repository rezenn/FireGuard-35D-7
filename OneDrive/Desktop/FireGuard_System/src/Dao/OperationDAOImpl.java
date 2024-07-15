package dao;

import model.Operation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class OperationDAOImpl implements OperationDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/fireGuard";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "root";
    private Connection conn;

    public OperationDAOImpl() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Operation getOperationById(int id) throws SQLException {
        String sql = "SELECT * FROM operation WHERE incidentId = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new Operation(
                    resultSet.getInt("incidentId"),
                    resultSet.getString("nameOfCaller"),
                    resultSet.getString("typeOfIncident"),
                    resultSet.getString("cause"),
                    resultSet.getInt("noOfInjured"),
                    resultSet.getInt("noOfCasualties"),
                    resultSet.getString("timeOfIncident"),
                    resultSet.getString("dateOfIncident"),
                    resultSet.getString("placeOfIncident"),
                    resultSet.getString("damageOfProperty")
                );
            } else {
                return null; // Return null if no operation found with the given id
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to retrieve operation from the database", e);
        }
    }

    @Override
    public void updateOperation(Operation operation) throws SQLException {
        String sql = "UPDATE operation SET nameOfCaller = ?, typeOfIncident = ?, cause = ?, noOfInjured = ?, noOfCasualties = ?, timeOfIncident = ?, dateOfIncident = ?, placeOfIncident = ?, damageOfProperty = ? WHERE incidentId = ?";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, operation.getNameOfCaller());
            statement.setString(2, operation.getTypeOfIncident());
            statement.setString(3, operation.getCause());
            statement.setInt(4, operation.getNoOfInjured());
            statement.setInt(5, operation.getNoOfCasualties());
            statement.setString(6, operation.getTimeOfIncident());
            statement.setString(7, operation.getDateOfIncident());
            statement.setString(8, operation.getPlaceOfIncident());
            statement.setString(9, operation.getDamageOfProperty());
            statement.setInt(10, operation.getIncidentId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to update operation in the database", e);
        }
    }

    @Override
    public boolean insertOperation(Operation operation) throws SQLException {
        String sql = "INSERT INTO operation (incidentId, nameOfCaller, typeOfIncident, cause, noOfInjured, noOfCasualties, timeOfIncident, dateOfIncident, placeOfIncident, damageOfProperty) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setInt(1, operation.getIncidentId());
            statement.setString(2, operation.getNameOfCaller());
            statement.setString(3, operation.getTypeOfIncident());
            statement.setString(4, operation.getCause());
            statement.setInt(5, operation.getNoOfInjured());
            statement.setInt(6, operation.getNoOfCasualties());
            statement.setString(7, operation.getTimeOfIncident());
            statement.setString(8, operation.getDateOfIncident());
            statement.setString(9, operation.getPlaceOfIncident());
            statement.setString(10, operation.getDamageOfProperty());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Failed to insert operation into the database", e);
        }
    }

    // Method to close the connection
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addOperation(Operation operation) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

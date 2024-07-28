package dao;

import model.Operation;
import java.sql.SQLException;

public interface OperationDAO {
    Operation getOperationById(int id) throws SQLException;
    void updateOperation(Operation operation) throws SQLException;
    boolean insertOperation(Operation operation) throws SQLException;
   // List<Operation> getAllOperations();

}

package dao;

import model.Staff;
import java.sql.SQLException;
import java.util.List;

public interface StaffDAO {
    Staff getStaffById(int id) throws SQLException;
    void updateStaff(Staff staff) throws SQLException;
    boolean insertStaff(Staff staff) throws SQLException;
    List<Staff> getAllStaffs() throws SQLException;
    void deleteStaff(String name)throws SQLException ;

}

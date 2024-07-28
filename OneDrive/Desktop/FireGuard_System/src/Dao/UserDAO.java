package Dao;

import java.sql.SQLException;

public interface UserDAO {
    boolean insertUser(User user) throws SQLException;
    boolean updateUser(User user) throws SQLException;
    boolean deleteUser(User user) throws SQLException;
    User validateUser(String email, String password, String userType) throws SQLException;
}

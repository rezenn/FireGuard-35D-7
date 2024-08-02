package dao;

import model.Schedule;
import java.sql.SQLException;
import java.util.List;

public interface ScheduleDAO {
    Schedule getScheduleById(int id) throws SQLException;
    void updateSchedule(Schedule schedule) throws SQLException;
    boolean insertSchedule(Schedule schedule) throws SQLException;
    List<Schedule> getAllSchedules() throws SQLException;
    void deleteScheduleByPhoneNumber(String phoneNumber) throws SQLException;

}


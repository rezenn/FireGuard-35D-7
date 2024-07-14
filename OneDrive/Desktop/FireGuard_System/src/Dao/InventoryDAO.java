
package Dao;

import model.Inventory;
import java.sql.SQLException;
import java.util.List;

public interface InventoryDAO {
    Inventory getInventoryById(int id);
    void updateInventory(Inventory inventory);
    boolean insertInventory(Inventory inventory) throws SQLException;
        List<Inventory> getAllInventories() throws SQLException; // Add this method

}
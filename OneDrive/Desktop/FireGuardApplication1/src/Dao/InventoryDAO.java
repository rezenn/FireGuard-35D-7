
package Dao;

import model.Inventory;
import java.sql.SQLException;

public interface InventoryDAO {
    boolean insertInventory(Inventory inventory) throws SQLException;
}

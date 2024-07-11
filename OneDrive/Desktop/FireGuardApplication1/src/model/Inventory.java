
package model;

public class Inventory {
    private String name;
    private String category;
    private String serialCode;
    private String quantity;
    private String manufactureDate;
    private String expiryDate;
    private String description;
    
    public Inventory(String name, String category, String serialCode, String quantity, String manufactureDate, String expiryDate, String description){
        this.name = name;
        this.category = category;
        this.serialCode = serialCode;
        this.quantity = quantity;
        this.manufactureDate = manufactureDate;
        this.expiryDate = expiryDate;
        this.description = description;
    }
    public String getName() { 
        return name; 
    }
    public void setName(String name) { 
        this.name = name; 
    }

    public String getCategory() { 
        return category; 
    }
    public void setCategory(String category) { 
        this.category = category; 
    }

    public String getSerialCode() { 
        return serialCode; 
    }
    public void setSerialCode(String serialCode) { 
        this.serialCode = serialCode; 
    }

    public String getQuantity() { 
        return quantity; 
    }
    public void setQuantity(String quantity) { 
        this.quantity = quantity; 
    }

    public String getManufactureDate() { 
        return manufactureDate; 
    }
    public void setManufactureDate(String manufactureDate) { 
        this.manufactureDate = manufactureDate; 
    }

    public String getExpiryDate() { 
        return expiryDate; 
    }
    public void setExpiryDate(String expiryDate) { 
        this.expiryDate = expiryDate; 
    }

    public String getDescription() { 
        return description; 
    }
    public void setDescription(String description) { 
        this.description = description; 
    }

    
}

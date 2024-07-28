package model;

public class Schedule {
    private String name;
    private String rank;
    private String phone_number;
    private String email;
    private String date;
    private String shift;

    public Schedule(String name, String rank, String phone_number, String email, String date, String shift) {
        this.name = name;
        this.rank = rank;
        this.phone_number = phone_number;
        this.email = email;
        this.date = date;
        this.shift = shift;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
  
    public String getPhone_number() {
        return phone_number;
    }
    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getShift() {
        return shift;
    }
    public void setShift(String shift) {
        this.shift = shift;
    }
}

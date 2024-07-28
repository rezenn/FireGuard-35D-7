package model;

public class Staff {
    private String name;
    private String staff_id;
    private String rank;
    private int age;
    private String phone_number;
    private String email;
    private String address;
    private String recruit_date;

    public Staff(String name, String staff_id, String rank, int age, String phone_number, String email, String address, String recruit_date) {
        this.name = name;
        this.staff_id = staff_id;
        this.rank = rank;
        this.age = age;
        this.phone_number = phone_number;
        this.email = email;
        this.address = address;
        this.recruit_date = recruit_date;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getStaff_id() {
        return staff_id;
    }
    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
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
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getRecruit_date() {
        return recruit_date;
    }
    public void setRecruit_date(String recruit_date) {
        this.recruit_date = recruit_date;
    }
}

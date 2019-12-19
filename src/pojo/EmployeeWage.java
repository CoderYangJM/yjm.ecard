package pojo;

public class EmployeeWage {
    String username;
    double wages;

    public EmployeeWage(String username, double wages) {
        this.username = username;
        this.wages = wages;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public double getWages() {
        return wages;
    }

    public void setWages(double wages) {
        this.wages = wages;
    }
}

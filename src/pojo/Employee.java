package pojo;

import java.sql.Date;

public class Employee {
    private String id;
    private String name;
    private String title;
    private String departmentNum;
    private String companyNum;
    private Date hireDate;
    private double wages;

    public Employee(String id,String name, String title, String departmentNum, String companyNum, Date hireDate, double wages) {
        this.id = id;
        this.name=name;
        this.title = title;
        this.departmentNum = departmentNum;
        this.companyNum = companyNum;
        this.hireDate = hireDate;
        this.wages = wages;
    }

    public Employee() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getHireDate() {
        return hireDate;
    }

    public void setHireDate(Date hireDate) {
        this.hireDate = hireDate;
    }

    public double getWages() {
        return wages;
    }

    public void setWages(double wages) {
        this.wages = wages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartmentNum() {
        return departmentNum;
    }

    public void setDepartmentNum(String departmentNum) {
        this.departmentNum = departmentNum;
    }

    public String getCompanyNum() {
        return companyNum;
    }

    public void setCompanyNum(String companyNum) {
        this.companyNum = companyNum;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", departmentNum='" + departmentNum + '\'' +
                ", companyNum='" + companyNum + '\'' +
                ", hireDate=" + hireDate +
                ", wages=" + wages +
                '}';
    }
}

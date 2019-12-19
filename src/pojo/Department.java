package pojo;

public class Department {
    private String departmentNum,companyNum,name,introduce;
    private int employeeNum;

    public Department(String departmentNum, String companyNum, String name, String introduce, int employeeNum) {
        this.departmentNum = departmentNum;
        this.companyNum = companyNum;
        this.name = name;
        this.introduce = introduce;
        this.employeeNum = employeeNum;
    }

    public Department(String departmentNum, String companyNum, String name, String introduce) {
        this.departmentNum = departmentNum;
        this.companyNum = companyNum;
        this.name = name;
        this.introduce = introduce;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public int getEmployeeNum() {
        return employeeNum;
    }

    public void setEmployeeNum(int employeeNum) {
        this.employeeNum = employeeNum;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentNum='" + departmentNum + '\'' +
                ", companyNum='" + companyNum + '\'' +
                ", name='" + name + '\'' +
                ", introduce='" + introduce + '\'' +
                ", employeeNum=" + employeeNum +
                '}';
    }
}

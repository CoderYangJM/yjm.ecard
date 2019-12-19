package pojo;

public class Company {
    private String companyNum, name, location, phoneNum,introduce;

    public Company(String companyNum, String name, String location, String phoneNum,String introduce) {
        this.companyNum = companyNum;
        this.name = name;
        this.location = location;
        this.phoneNum = phoneNum;
        this.introduce=introduce;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyNum='" + companyNum + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}

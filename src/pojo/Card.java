package pojo;

import java.util.Date;

public class Card {
    private String id,name, sex, address, phoneNum, title, company;
    private int shareNum, collectionNum,cardNum;

    public Card(String id, int cardNum, String name, String sex, String address, String phoneNum, String company, String title, int shareNum, int collectionNum) {
        this.id = id;
        this.cardNum = cardNum;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.phoneNum = phoneNum;
        this.title = title;
        this.company = company;
        this.shareNum = shareNum;
        this.collectionNum = collectionNum;
    }

    public Card(int cardNum, String name, String sex, String address, String phoneNum, String company, String title, int shareNum, int collectionNum) {
        this.cardNum = cardNum;
        this.name = name;
        this.sex = sex;
        this.address = address;
        this.phoneNum = phoneNum;
        this.title = title;
        this.company = company;
        this.shareNum = shareNum;
        this.collectionNum = collectionNum;
    }

    public Card() {

    }


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCardNum() {
        return cardNum;
    }

    public void setCardNum(int cardNum) {
        this.cardNum = cardNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getShareNum() {
        return shareNum;
    }

    public void setShareNum(int shareNum) {
        this.shareNum = shareNum;
    }

    public int getCollectionNum() {
        return collectionNum;
    }

    public void setCollectionNum(int collectionNum) {
        this.collectionNum = collectionNum;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id='" + id + '\'' +
                ", cardNum='" + cardNum + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", title='" + title + '\'' +
                ", company='" + company + '\'' +
                ", shareNum=" + shareNum +
                ", collectionNum=" + collectionNum +
                '}';
    }
}

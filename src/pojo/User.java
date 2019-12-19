package pojo;

public class User {
    private String id;//账号唯一标识
    private String userName;//昵称
    private String password;
    private String realName;
    public User(String id,String userName, String password) {
        this.id=id;
        this.userName = userName;
        this.password = password;
    }

    public User(String id, String userName, String password, String realName) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.realName = realName;
    }

    public User() {

    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public User(String id, String password) {
        this.id=id;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}

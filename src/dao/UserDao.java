package dao;

import pojo.HR;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    /***
     * 向user表插入用户数据。
     * @param user
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean saveUser(User user) throws SQLException, ClassNotFoundException {
        //查询是否已被注册
        if (findUserById(user.getId())) {
            //账号已存在
            return false;
        }
        String sql = "insert into user values(?,?,?,?)";
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setString(1, user.getId());
        statement.setString(2, user.getUserName());
        statement.setString(3, user.getPassword());
        statement.setString(4,user.getRealName());
        int result = statement.executeUpdate();
        statement.close();
        DBManager.disConnection();
        if (result > 0)
            return true;
        else return false;
    }

    /***
     * 根据id查询是否账号已被注册，主要用于注册功能
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean findUserById(String id) throws SQLException, ClassNotFoundException {
        String sql = "select count(1) from user where id=?";
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        return isExist(statement);
    }


    public String getUserRealNameById(String id) throws SQLException, ClassNotFoundException {
        String sql = "select realname from user where id=?";
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet set=statement.executeQuery();
        String name=null;
        while (set.next()){
            name=set.getString(1);
        }
        return name;
    }
    /***
     * 根据用户昵称查询
     * @param userName
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */

    public boolean findUserByName(String userName) throws SQLException, ClassNotFoundException {
        String sql = "select count(1) from user where username=?";
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, userName);
        return isExist(statement);
    }

    /***
     * 根据sql查询语句查询是否存在，若存在查询对象则返回true
     * @param statement
     * @return
     * @throws SQLException
     */
    private boolean isExist(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getInt(1) > 0) {
                DBManager.disConnection();
                return true;
            }
        }
        statement.close();
        DBManager.disConnection();
        return false;
    }

    /***
     * 根据账号id和密码查询，若数据库存在此账号则返回true否则返回false
     * 主要用于登录功能
     * @param user
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean findUser(User user) throws SQLException, ClassNotFoundException {
        String sql = "select count(1) from user where id=? and password=? ";

        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getId());
        statement.setString(2, user.getPassword());
        return isExist(statement);
    }

    /***
     * 查询管理员表
     * @param id
     * @return
     */
    public boolean isManager(String id) throws SQLException, ClassNotFoundException {
        String sql = "select count(1) from admin where id=?";
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        return isExist(statement);
    }

    /***
     * 查询是否为HR
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public boolean isHR(String id) throws SQLException, ClassNotFoundException {
        String sql = "select count(1) from employee where id=? and title=?";
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        statement.setString(2, "HR");
        return isExist(statement);
    }

    //当登录用户为HR时调用此方法。查询他是某一个公司的HR
    public String findCompany(String id) throws SQLException, ClassNotFoundException {
        String sql = "select companyNum from employee where id=?";
        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1,id);
        ResultSet resultSet = statement.executeQuery();
        String companyNum = null;
        while (resultSet.next()) {
            companyNum = resultSet.getString(1);
        }
        return companyNum;
    }

    //根据id和密码查询出用户的个人信息并返回一个user对象
    public User getUserByIP(String id,String password) throws SQLException, ClassNotFoundException {
        String sql = "select userName,realName from user where id=? and password=? ";

        Connection connection = DBManager.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        statement.setString(2, password);
        User user=null;
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            user=new User(id,resultSet.getString(1),password,resultSet.getString(2));
        }
        statement.close();
        DBManager.disConnection();
        return user;
    }

    public boolean updateUser(User user, String newUserName, String newRealName, String newPassword) throws SQLException, ClassNotFoundException {
        String sql="update user set username=?,realName=?,password=? where id=? and password=?";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        statement.setString(1,newUserName);
        statement.setString(2,newRealName);
        statement.setString(3,newPassword);
        statement.setString(4,user.getId());
        statement.setString(5,user.getPassword());

        int result=statement.executeUpdate();
        if (result>0){
            //若数据库中的信息更新成功，更新当前内存中保存的用数据
            user.setRealName(newRealName);
            user.setUserName(newUserName);
            user.setPassword(newPassword);
            return true;
        }
        return false;
    }

    public String getUserNameById(String id) throws SQLException, ClassNotFoundException {
        String sql="select username from user where id=?";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        statement.setString(1,id);
        ResultSet resultSet=statement.executeQuery();
        String username=null;
        while (resultSet.next()){
           username= resultSet.getString(1);
        }
        statement.close();
        return username;
    }
}

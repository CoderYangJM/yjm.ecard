package dao;

import pojo.CollectedShareCard;
import pojo.EmployeeWage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticDao {
    //查询被收藏次数最多的n张名片的卡号,被收藏数及其主人昵称
    public List<CollectedShareCard> getMaxCollectionCarAndUser(int n) throws SQLException, ClassNotFoundException {
        String sql="select cardNum,id,collectionNum from card order by  collectionNum desc limit ?";
        return getCollectedShareCards(n, sql);
    }
    //查询分享次数最多的n张名片的卡号,分享数及其主人昵称
    public List<CollectedShareCard> getMaxShareCarAndUser(int n) throws SQLException, ClassNotFoundException {
        String sql="select cardNum,id,shareNum from card order by  shareNum desc limit ?";
        return getCollectedShareCards(n, sql);
    }

    private List<CollectedShareCard> getCollectedShareCards(int n, String sql) throws SQLException, ClassNotFoundException {
        PreparedStatement statement= DBManager.getConnection().prepareStatement(sql);
        statement.setInt(1,n);
        ResultSet set=statement.executeQuery();
        List<CollectedShareCard> maxCollection=new ArrayList<>(10);
        String id,userName;
        UserDao userDao=new UserDao();
        while (set.next()){
            id=set.getString(2);
            //根据id查询昵称
            userName=userDao.getUserNameById(id);
            maxCollection.add(new CollectedShareCard(set.getInt(1),userName,set.getInt(3)));
        }
        statement.close();
        DBManager.disConnection();
        return maxCollection;
    }

    public List<EmployeeWage> getMaxEmployeeWages(int i) throws SQLException, ClassNotFoundException {
        String sql="select wages,id from employee order by wages desc limit ?";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        List<EmployeeWage> employeeWages=new ArrayList<>(10);
        statement.setInt(1,i);
        ResultSet set=statement.executeQuery();
        String id,userName;
        UserDao userDao=new UserDao();
        while (set.next()){
            id=set.getString(2);
            //根据id查询昵称
            userName=userDao.getUserNameById(id);
            employeeWages.add(new EmployeeWage(userName,set.getDouble(1)));
        }
        statement.close();
        DBManager.disConnection();
        return employeeWages;
    }
}

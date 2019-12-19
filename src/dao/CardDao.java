package dao;


import pojo.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CardDao {
    /**
     * 根据传入的Id，将card表中所有对应此id的数据取出
     * 并使用List传出
     * @param id
     * @return
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public List<Card> loadCardById(String id) throws SQLException, ClassNotFoundException {
        String sql="select cardNum,name,sex,address,phoneNum,company,title,shareNum,collectionNum from card where id=?";
        Connection connection=DBManager.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<Card> cards=new ArrayList<>(10);
        String name,sex,address,phoneNum,title,company;
        int shareNum,cardNum,collectionNum;

        //获取卡号、姓名、性别、地址、电话、公司、职位、分享次数、收藏次数。
        while (resultSet.next()){
            cardNum=resultSet.getInt(1);
            name=resultSet.getString(2);
            sex=resultSet.getString(3);
            address=resultSet.getString(4);
            phoneNum=resultSet.getString(5);
            company=resultSet.getString(6);
            title=resultSet.getString(7);
            shareNum=resultSet.getInt(8);
            collectionNum=resultSet.getInt(9);
            //插入ArrayList
            cards.add(new Card(id,cardNum,name,sex,address,phoneNum,company,title,shareNum,collectionNum));
        }
        return cards;
    }

    /***
     * 主要用于收藏、分享功能，将传入的id及卡号保存进collection表
     * 并将此卡分享及收藏次数+1
     * @param id
     * @param cardNum
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addCollection(String id, int cardNum) throws SQLException, ClassNotFoundException {
        String sql="insert into collection values(?,?)";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        statement.setString(1,id);
        statement.setInt(2,cardNum);
        statement.execute();
        //分享次数+1，收藏次数+1；
        HashMap<String,Integer> hashMap=getCollectionAndShareNum(cardNum);
        int collectionNum=hashMap.get("collectionNum")+1;
        int shareNum=hashMap.get("shareNum")+1;
        String sql2="update card set collectionNum=?,shareNum=? where cardNum=?";
        PreparedStatement statement2=DBManager.getConnection().prepareStatement(sql2);
        statement2.setInt(1,collectionNum);
        statement2.setInt(2,shareNum);
        statement2.setInt(3,cardNum);
        statement2.execute();
        //释放资源。
        statement.close();
        statement2.close();
        DBManager.disConnection();
    }
    //获取分享次数与收藏次数，保存至hashMap，Key分别为collectionNum、shareNum
    public HashMap<String,Integer> getCollectionAndShareNum(int cardNum) throws SQLException, ClassNotFoundException {
        HashMap<String,Integer> hashMap=new HashMap<>(2);
        String sql="select shareNum,collectionNum from card where cardNum=?";
        PreparedStatement preparedStatement=DBManager.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1,cardNum);
        ResultSet set=preparedStatement.executeQuery();
        while (set.next()){
            hashMap.put("shareNum",set.getInt(1));
            hashMap.put("collectionNum",set.getInt(2));
        }
        preparedStatement.close();
        return hashMap;
    }

    /***
     * 根据卡号删除此卡所有记录，包括其他人收藏夹里的此卡
     * @param cardNum
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteCardByNum(int cardNum) throws SQLException, ClassNotFoundException {
        //先删除他人收藏夹的此名片。
        String sql="delete from collection where cardNum=?";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        statement.setInt(1,cardNum);
        statement.execute();
        //从名片表删除名片
        String sql2="delete from card where cardNum=?";
        PreparedStatement statement2=DBManager.getConnection().prepareStatement(sql2);
        statement2.setInt(1,cardNum);
        statement2.execute();
        //释放资源
        statement.close();
        statement2.close();
        DBManager.disConnection();
    }

    /***
     * 添加卡片
     * @param card
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void addCard(Card card) throws SQLException, ClassNotFoundException {
        String sql="insert into card(id,name,sex,address,phoneNum,company,title,shareNum,collectionNum) values(?,?,?,?,?,?,?,0,0)";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        //设置基本信息
        statement.setString(1,card.getId());
        statement.setString(2,card.getName());
        statement.setString(3,card.getSex());
        statement.setString(4,card.getAddress());
        statement.setString(5,card.getPhoneNum());
        statement.setString(6,card.getCompany());
        statement.setString(7,card.getTitle());
        //执行sql语句
        statement.execute();
        //释放资源
        statement.close();
        DBManager.disConnection();
    }

    /**
     * 更新卡片
     * @param card
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void updateCard(Card card) throws SQLException, ClassNotFoundException {
        String sql="update card set name=?,sex=?,address=?,phoneNum=?,company=?,title=? where cardNum=?";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        statement.setString(1,card.getName());
        statement.setString(2,card.getSex());
        statement.setString(3,card.getAddress());
        statement.setString(4,card.getPhoneNum());
        statement.setString(5,card.getCompany());
        statement.setString(6,card.getTitle());
        statement.setInt(7,card.getCardNum());
        statement.execute();
        statement.close();
        DBManager.disConnection();
    }

    //根据卡号加载名片
    public Card getCardByCardNum(int cardNum) throws SQLException, ClassNotFoundException {
        String sql="select name,sex,address,phoneNum,company,title,shareNum,collectionNum from card where cardNum=?";
        Connection connection=DBManager.getConnection();
        PreparedStatement preparedStatement=connection.prepareStatement(sql);
        preparedStatement.setInt(1,cardNum);
        ResultSet resultSet=preparedStatement.executeQuery();
        String name,sex,address,phoneNum,title,company;
        int shareNum,collectionNum;
        Card card=null;
        //获取卡号、姓名、性别、地址、电话、公司、职位、分享次数、收藏次数。
        while (resultSet.next()){
            name=resultSet.getString(1);
            sex=resultSet.getString(2);
            address=resultSet.getString(3);
            phoneNum=resultSet.getString(4);
            company=resultSet.getString(5);
            title=resultSet.getString(6);
            shareNum=resultSet.getInt(7);
            collectionNum=resultSet.getInt(8);
            //实例化卡
            card=new Card(cardNum,name,sex,address,phoneNum,company,title,shareNum,collectionNum);
        }
        preparedStatement.close();
        DBManager.disConnection();
        return card;
    }
}

package dao;

import pojo.Card;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionDao {
    //根据id获取收藏夹里的名片
    public List<Card> getCollectionCard(String id) throws SQLException, ClassNotFoundException {
        List<Card> cards=new ArrayList<>(10);
        //根据id从collection里取出对应的卡号
        String sql="select cardNum from collection where id=?";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        statement.setString(1,id);
        ResultSet cardNums=statement.executeQuery();

        //根据卡号从card表取出卡片信息
        String sql2="select name,sex,address,phoneNum,company,title,shareNum,collectionNum from card where cardNum=?";
        PreparedStatement preparedStatement=DBManager.getConnection().prepareStatement(sql2);
        ResultSet resultSet=null;
        String  name,sex,address,phoneNum,title,company;
        int shareNum,collectionNum,cardNum;
        while (cardNums.next()){
            //设置要查询的卡的卡号
            cardNum=cardNums.getInt(1);
            preparedStatement.setInt(1,cardNum);
            //根据卡号执行查询
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                name=resultSet.getString(1);
                sex=resultSet.getString(2);
                address=resultSet.getString(3);
                phoneNum=resultSet.getString(4);
                company=resultSet.getString(5);
                title=resultSet.getString(6);
                shareNum=resultSet.getInt(7);
                collectionNum=resultSet.getInt(8);
                //实例化卡并插入cards
                cards.add(new Card(cardNum,name,sex,address,phoneNum,company,title,shareNum,collectionNum));
            }
        }
        //释放资源
        statement.close();
        preparedStatement.close();
        DBManager.disConnection();
        return cards;
    }

    /**
     * 根据id与卡号从collection表删除收藏的卡
     * @param cardNum
     * @param id
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    public void deleteCollection(int cardNum, String id) throws SQLException, ClassNotFoundException {
        String sql="delete from collection where cardNum=? and id=?";
        PreparedStatement statement=DBManager.getConnection().prepareStatement(sql);
        statement.setInt(1,cardNum);
        statement.setString(2,id);
        statement.execute();

        //将传入名片的收藏次数-1
        CardDao cardDao=new CardDao();
        Map<String,Integer> map=cardDao.getCollectionAndShareNum(cardNum);
        int collectionNum=map.get("collectionNum")-1;
        String sql2="update card set collectionNum=? where cardNum=?";
        PreparedStatement statement1=DBManager.getConnection().prepareStatement(sql2);
        statement1.setInt(1,collectionNum);
        statement1.setInt(2,cardNum);
        statement1.execute();

        //释放资源
        statement.close();
        statement1.close();
        DBManager.disConnection();
    }

}

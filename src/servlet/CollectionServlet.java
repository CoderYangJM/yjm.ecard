package servlet;

import dao.CardDao;
import dao.CollectionDao;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/inner/CollectionServlet")
public class CollectionServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String op=req.getParameter("op");
        if ("delete".equals(op)){
            //删除收藏夹里的名片
            //获取id
            String id=req.getParameter("id");
            //获取卡号
            int cardNum=Integer.parseInt(req.getParameter("cardNum"));
            CollectionDao collectionDao=new CollectionDao();
            try {
                collectionDao.deleteCollection(cardNum,id);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                //重新加载收藏夹
                resp.sendRedirect("LoadCollectionCard");
            }
        }else if ("collection".equals(op)){
            User user=(User)(req.getSession().getAttribute("user"));
            String id=user.getId();
            int cardNum=Integer.parseInt(req.getParameter("cardNum"));
            CardDao cardDao=new CardDao();
            try {
                cardDao.addCollection(id,cardNum);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                //重新加载收藏夹
                resp.sendRedirect("LoadCollectionCard");
            }
        }
    }
}

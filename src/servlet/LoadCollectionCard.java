package servlet;

import dao.CollectionDao;
import pojo.Card;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "LoadCollectionCard", urlPatterns = "/inner/LoadCollectionCard")
public class LoadCollectionCard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    /**
     * 根据用户id加载收藏夹里的名片
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user=(User) req.getSession().getAttribute("user");
        CollectionDao collectionDao=new CollectionDao();
        try {
            List<Card> collections=collectionDao.getCollectionCard(user.getId());
            req.getSession().setAttribute("Collections",collections);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            resp.sendRedirect("../collection.jsp");
        }

    }
}

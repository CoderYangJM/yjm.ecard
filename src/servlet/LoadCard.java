package servlet;

import dao.CardDao;
import pojo.Card;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "LoadCard", urlPatterns = "/inner/LoadCard")
public class LoadCard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CardDao cardDao=new CardDao();
        HttpSession session=req.getSession();
        User user=(User) session.getAttribute("user");
        try {
            List<Card> cards=cardDao.loadCardById(user.getId());
            //将名片信息保存进session
            session.setAttribute("Cards",cards);
            // 跳转至个人名片界面
//            req.getRequestDispatcher("/personECV.jsp").forward(req,resp);
            resp.sendRedirect("../personECV.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package servlet;

import dao.CardDao;
import pojo.Card;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/inner/CardServlet")
public class CardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        CardDao cardDao = new CardDao();
        if ("share".equals(op)) {
            //分享名片
            int cardNum = Integer.parseInt(req.getParameter("cardNum"));
            String id = req.getParameter("id");
            try {
                cardDao.addCollection(id, cardNum);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                req.getRequestDispatcher("LoadCard").forward(req, resp);
            }
        } else if ("delete".equals(op)) {
            //删除名片
            //获取名片号
            int cardNum = Integer.parseInt(req.getParameter("cardNum"));
            try {
                //根据名片号删除卡片
                cardDao.deleteCardByNum(cardNum);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                req.getRequestDispatcher("LoadCard").forward(req, resp);
            }
        } else if ("add".equals(op)) {
            //添加名片
            //从request作用域获取信息，实例化名片类
            Card card = createCard(req, resp);
            try {
                //添加卡片
                cardDao.addCard(card);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                req.getRequestDispatcher("LoadCard").forward(req, resp);
            }
        } else if ("update".equals(op)) {
            //从request作用域获取信息，实例化名片类
            Card card = createCard(req, resp);
            try {
                //更新卡片
                cardDao.updateCard(card);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally {
                req.getRequestDispatcher("LoadCard").forward(req, resp);
            }

        }
    }

    private Card createCard(HttpServletRequest req, HttpServletResponse resp) {
        //创建名片
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String sex = req.getParameter("sex");
        String address = req.getParameter("address");
        String phoneNum = req.getParameter("phoneNum");
        String company = req.getParameter("company");
        String title = req.getParameter("title");
        int cardNum = 0;
        String cardNumString = req.getParameter("cardNum");
        if (cardNumString != null && !"".equals(cardNumString)) {
            cardNum = Integer.parseInt(cardNumString);
        }
        Card card = null;
        card = new Card(id, cardNum, name, sex, address, phoneNum, company, title, 0, 0);
        return card;
    }
}

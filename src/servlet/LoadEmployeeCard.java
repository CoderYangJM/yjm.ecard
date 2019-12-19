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
import java.util.List;

@WebServlet(urlPatterns = "/inner/manager/LoadEmployeeCard")
public class LoadEmployeeCard extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    /**
     * 根据传入的id加载此用户的所有名片
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id=req.getParameter("id");
        CardDao cardDao=new CardDao();
        try {
            //加载名片
            List<Card> cards=cardDao.loadCardById(id);
            //将取出的名片信息保存进session作用域
            req.getSession().setAttribute("EmployeeCards",cards);
            //跳转至员工名片界面
          //  req.getRequestDispatcher("/employeeCard.jsp").forward(req,resp);
            resp.sendRedirect("../../employeeCard.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

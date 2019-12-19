package servlet;

import dao.UserDao;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = "/inner/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //更新用户信息
        HttpSession session=req.getSession();
        User user=(User)session.getAttribute("user");
        String newUserName=req.getParameter("UpdateUserName");
        String newRealName=req.getParameter("UpdateRealName");
        String newPassword=req.getParameter("UpdatePassword");
        UserDao userDao=new UserDao();
        try {
            if (userDao.updateUser(user,newUserName,newRealName,newPassword)){
                //更新成功
                //重新插入新的用户数据进入session
                session.setAttribute("user",user);
                //保存修改状态为true
                session.setAttribute("UpdateUserState",true);
                //跳回个人名片界面
                resp.sendRedirect("../personECV.jsp");
            }else {
                //保存修改状态为false
                session.setAttribute("UpdateUserState",false);
                //跳回修改界面
                resp.sendRedirect("../updateUser.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

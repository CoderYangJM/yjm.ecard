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

@WebServlet(name = "RegisterServlet", urlPatterns = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String id = req.getParameter("UserId");
        String name = req.getParameter("RegisterUserName");
        String password = req.getParameter("RegisterPassword");
        String realName=req.getParameter("RegisterRealName");
        try {
            //根据传入的信息实例化User类保存此user
            //判断是否保存成功
            if (userDao.saveUser(new User(id, name, password,realName))) {
                //注册成功
                //提示注册成功
                resp.getWriter().print("<script>alert(\"注册成功!\");window.location.href='login.jsp'</script>"); ;
                //跳转至登录界面
               // req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                //注册失败
                //重定向至注册界面
                resp.sendRedirect("register.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

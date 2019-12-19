package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(urlPatterns = "/inner/LogoutServlet")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //清空session数据
        HttpSession session=req.getSession();
        //session.invalidate();
        //获取所有session数据的名字
        Enumeration<String> names=session.getAttributeNames();
        while (names.hasMoreElements()){
            session.removeAttribute(names.nextElement());
        }

        //跳回登录界面
        resp.sendRedirect("../login.jsp");
    }
}

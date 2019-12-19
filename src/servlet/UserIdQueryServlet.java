package servlet;

import dao.EmployeeDao;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/UserIdQueryServlet")
public class UserIdQueryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取传进来的ID
        String id=req.getParameter("UserId");
        PrintWriter writer=resp.getWriter();
        UserDao userDao=new UserDao();
        try {
            if (userDao.findUserById(id)){
                EmployeeDao employeeDao=new EmployeeDao();
                if(employeeDao.isEmployee(id)){
                    //存在此id,且为员工
                    writer.println("{\"result\":\"2\"}");
                }else {
                    //不为员工
                    writer.println("{\"result\":\"1\"}");
                }
            }else {
                writer.println("{\"result\":\"0\"}");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}

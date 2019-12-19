package servlet;

import dao.DepartmentDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/***
 * 用以删除部门
 */

@WebServlet(urlPatterns = "/inner/manager/DeleteDepartment")
public class DeleteDepartment extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据传入的公司号及部门号删除部门
        //获取公司号
        String companyNum=req.getParameter("companyNum");
        //获取部门号
        String departmentNum=req.getParameter("departmentNum");
        DepartmentDao departmentDao=new DepartmentDao();
        try {
            //删除部门
            departmentDao.deleteDepartment(companyNum,departmentNum);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            //删除成功之后重新加载部门
            req.getRequestDispatcher("LoadDepartment").forward(req,resp);
        }
    }
}

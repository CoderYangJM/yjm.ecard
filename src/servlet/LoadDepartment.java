package servlet;

import dao.CompanyDao;
import dao.DepartmentDao;
import pojo.Company;
import pojo.Department;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoadDepartment", urlPatterns = "/inner/manager/LoadDepartment")
public class LoadDepartment extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyNum=req.getParameter("companyNum");
        DepartmentDao departmentDao = new DepartmentDao();
        try {
            HttpSession session = req.getSession();
            if (companyNum==null){
                companyNum=String.valueOf(session.getAttribute("employeeCompany"));
                req.setAttribute("companyNum",companyNum);
            }
            //从数据库获取所有的部门数据
            List<Department> departments = departmentDao.findAllDepartment(companyNum);
            //将数据存入session
            session.setAttribute("Departments", departments);
            //添加公司号进入session作用域
            session.setAttribute("companyNum",companyNum);
            //跳转至部门管理界面
          //  req.getRequestDispatcher("/departmentMV.jsp").forward(req, resp);
            resp.sendRedirect("../../departmentMV.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

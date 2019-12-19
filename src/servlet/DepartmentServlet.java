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
import java.sql.SQLException;

@WebServlet(urlPatterns = "/inner/manager/DepartmentServlet")
public class DepartmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取操作方式
        String op = req.getParameter("op");
        String companyNum=req.getParameter("companyNum");
        //从request获取部门
        String departmentNum = req.getParameter("departmentNum");
        //公司名称
        String departmentName = req.getParameter("departmentName");
        //公司简介
        String departmentIntroduce = req.getParameter("departmentIntroduce");

        HttpSession session = req.getSession();
        Department department =new Department(departmentNum,companyNum,departmentName,departmentIntroduce);
        DepartmentDao departmentDao=new DepartmentDao();
        if ("add".equals(op)) {
            try {
                //向数据库插入公司数据
                if (departmentDao.addDepartment(department)) {
                    //插入成功
                    session.setAttribute("addDepartmentState", true);//设置插入状态
                    req.getRequestDispatcher("LoadDepartment").forward(req, resp);//重新加载公司
                } else {
                    //插入失败
                    session.setAttribute("addCompanyState", false);//设置插入状态
                    req.getRequestDispatcher("../../departmentMV.jsp").forward(req, resp);//跳回原来界面
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if("update".equals(op)){
            //do update
            try {
                //更新部门信息
                departmentDao.updateDepartment(department);
                //重新加载部门
                req.getRequestDispatcher("LoadDepartment").forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

}

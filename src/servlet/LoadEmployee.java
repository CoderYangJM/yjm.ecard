package servlet;

import dao.EmployeeDao;
import pojo.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/***
 * 专门用以加载员工
 */
@WebServlet("/inner/manager/LoadEmployee")
public class LoadEmployee extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String departmentNum = req.getParameter("departmentNum");
        String companyNum = req.getParameter("companyNum");
        //第一次加载得获取页数并保存
        if(req.getParameter("employeeNum")!=null){
            int employeeNum=Integer.parseInt(req.getParameter("employeeNum"));
            int pagElementNum=1;
            //计算改分多少页
            if(employeeNum%20!=0){
                //如果模20不为零,则多分配一页
                pagElementNum=employeeNum/20+1;
            }else {
                pagElementNum=employeeNum/20;
            }
            req.getSession().setAttribute("pagElementNum",pagElementNum);
            //将部门号与公司号保存进作用域中
            req.getSession().setAttribute("departmentNum",departmentNum);
            req.getSession().setAttribute("companyNum",companyNum);
        }
        //获取当前页数,默认为第一页
        int num=1;
        String numString=req.getParameter("pageNum");
        if (numString!=null && !" ".equals(numString)){
            num=Integer.parseInt(numString);
        }
        EmployeeDao employeeDao = new EmployeeDao();
        try {
            //根据公司号及部门号加载所有的员工信息
            //根据页号从数据库加载(num-1)*20~num*20-1的数据
            List<Employee> employeeList = employeeDao.findEmployeeByCD(companyNum, departmentNum,(num-1)*20);
            //将员工信息保存进session作用域
            req.getSession().setAttribute("employeeList", employeeList);
            //保存当前页数进入session
            req.getSession().setAttribute("currentPage",num);
            //跳转至员工信息界面
            //req.getRequestDispatcher("../../employee.jsp").forward(req, resp);
            resp.sendRedirect("../../employee.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

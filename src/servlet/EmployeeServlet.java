package servlet;

import dao.EmployeeDao;
import dao.UserDao;
import pojo.Employee;
import pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/inner/manager/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String op = req.getParameter("op");
        EmployeeDao employeeDao = new EmployeeDao();
        HttpSession session = req.getSession();
        //处理添加请求
        if ("add".equals(op)) {
            //从request作用域获取值并实例化员工
            Employee employee=creatEmployee(req,resp);
            try {
                //如果插入的employee职位为HR，而这个员工已经是HR则直接返回
                UserDao userDao=new UserDao();
                if (employee.getTitle().equals("HR") && userDao.isHR(employee.getId())){
                    session.setAttribute("addEmployeeState", false);//设置保存状态
                    req.getRequestDispatcher("LoadDepartment").forward(req, resp);//重新加载部门
                }else {
                    try {
                        //向数据库插入公司数据
                        if (employeeDao.addEmployee(employee)) {
                            session.setAttribute("addEmployeeState", true);//设置保存状态
                        } else {
                            session.setAttribute("addEmployeeState", false);//设置保存状态
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }finally {
                        req.getRequestDispatcher("LoadDepartment").forward(req, resp);//重新加载部门
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if ("update".equals(op)) {
            //更新员工
            //从request作用域获取值并实例化员工
            Employee employee=creatEmployee(req,resp);
            //格式化日期
            String date=req.getParameter("date");
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            try {
                java.util.Date formatedDate=format.parse(date);
                //更新日期
                employee.setHireDate(new Date(formatedDate.getTime()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            try {
                employeeDao.updateEmployee(employee);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                req.getRequestDispatcher("LoadEmployee").forward(req,resp);
            }
        }else if ("delete".equals(op)){
            //删除
            String companyNum=req.getParameter("companyNum");
            String departmentNum=req.getParameter("departmentNum");
            String id=req.getParameter("id");
            //根据公司号、部门号、id从数据库删除此员工
            try {
                employeeDao.deleteByCDI(companyNum,departmentNum,id);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }finally {
                req.getRequestDispatcher("LoadEmployee").forward(req,resp);
            }
        }else {
            System.out.println("Do nothing");
        }
    }

    private Employee creatEmployee(HttpServletRequest req,HttpServletResponse resp) {
        //获取员工ID,companyNum,departmentNum,title
        String id = req.getParameter("id");
        String companyNum = req.getParameter("companyNum");
        String departmentNum = req.getParameter("departmentNum");
        String title = req.getParameter("title");
        double wages = Double.valueOf(req.getParameter("wages"));
        UserDao userDao = new UserDao();
        String realName = null;
        try {
            realName = userDao.getUserRealNameById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //实例化员工
        Employee employee = new Employee(id, realName, title, departmentNum, companyNum, new Date(System.currentTimeMillis()), wages);
        return  employee;
    }
}

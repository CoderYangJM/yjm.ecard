package servlet;

import dao.CompanyDao;
import pojo.Company;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/***
 * 用以添加公司
 */
@WebServlet(urlPatterns = "/inner/manager/company/CompanyServlet")
public class CompanyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取操作方式
        String op = req.getParameter("op");
        //从request获取公司
        String companyNum = req.getParameter("companyNum");
        //公司名称
        String companyName = req.getParameter("companyName");
        //公司地址
        String companyLocation = req.getParameter("companyLocation");
        //前台电话
        String companyPhone = req.getParameter("companyPhoneNum");
        //公司简介
        String companyIntroduce = req.getParameter("companyIntroduce");

        HttpSession session = req.getSession();
        //根据获取的信息实例化公司类
        Company company = new Company(companyNum, companyName, companyLocation, companyPhone, companyIntroduce);
        CompanyDao companyDao = new CompanyDao();
        if ("add".equals(op)) {
            try {
                //向数据库插入公司数据
                if (companyDao.addCompany(company)) {
                    session.setAttribute("addCompanyState", true);//设置保存状态
                    req.getRequestDispatcher("LoadCompany").forward(req, resp);//重新加载公司
                } else {
                    session.setAttribute("addCompanyState", false);//设置保存状态
                    req.getRequestDispatcher("companyMV.jsp").forward(req, resp);//跳回保存界面
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if("update".equals(op)){
            //更新公司信息
            try {
                companyDao.updateCompany(company);
                req.getRequestDispatcher("LoadCompany").forward(req, resp);//重新加载公司
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
}

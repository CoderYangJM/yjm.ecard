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
import java.util.List;

/***
 * 用以加载公司数据
 */
@WebServlet(name = "LoadCompany", urlPatterns = "/inner/manager/company/LoadCompany")
public class LoadCompany extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CompanyDao companyDao=new CompanyDao();
        try {
            List<Company> companies=companyDao.findAllCompany();//从数据库获取所有的公司数据
            HttpSession session=req.getSession();
            session.setAttribute("Companies",companies);//将数据存入session
            //跳转至管理者界面
           // req.getRequestDispatcher("/companyMV.jsp").forward(req,resp);
            resp.sendRedirect("../../../companyMV.jsp");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}

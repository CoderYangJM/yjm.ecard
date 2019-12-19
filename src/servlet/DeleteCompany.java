package servlet;

import dao.CompanyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * 专门用以删除公司
 */
@WebServlet(urlPatterns = "/inner/manager/company/DeleteCompany")
public class DeleteCompany extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //根据传入的公司号删除公司
        String companyNum = req.getParameter("companyNum");
        CompanyDao companyDao = new CompanyDao();
        try {
            companyDao.deleteCompany(companyNum);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {//无论是否成功删除都重新加载公司
            req.getRequestDispatcher("LoadCompany").forward(req,resp);
        }
    }
}

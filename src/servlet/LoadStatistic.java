package servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import dao.StatisticDao;
import pojo.CollectedShareCard;
import pojo.EmployeeWage;

@WebServlet(urlPatterns = "/inner/manager/company/LoadStatistic")
public class LoadStatistic extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StatisticDao statisticDao=new StatisticDao();
        HttpSession session=req.getSession();
        try {
            //获取收藏数最多的十张名片的卡号及其主人的昵称
            List<CollectedShareCard> maxCollection=statisticDao.getMaxCollectionCarAndUser(10);
            //获取分享数最多的十张名片的卡号及其主人的昵称
            List<CollectedShareCard> maxShare=statisticDao.getMaxShareCarAndUser(10);
            //获取薪资最高的十个员工的薪资及其昵称
            List<EmployeeWage> employeeWages=statisticDao.getMaxEmployeeWages(10);
            //保存进session
            session.setAttribute("maxCollection",maxCollection);
            session.setAttribute("maxShare",maxShare);
            session.setAttribute("employeeWages",employeeWages);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        resp.sendRedirect("../../../statistic.jsp");
    }
}

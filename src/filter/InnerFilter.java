package filter;

import pojo.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 内部servlet必须登录才能访问
 */
@WebFilter(urlPatterns = "/inner/*")
public class InnerFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req1 = (HttpServletRequest) req;
        HttpSession session = req1.getSession();
        //用户session不为空，证明已登录
        if (session.getAttribute("user") != null) {
            chain.doFilter(req, resp);
        } else {
            //不登录则直接跳回登录界面
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect("../login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

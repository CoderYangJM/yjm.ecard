package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 部门管理界面只能由HR或管理者访问
 */
@WebFilter(urlPatterns = "/inner/department/*")
public class ManagerFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpSession session=request.getSession();
        String role=String.valueOf(session.getAttribute("role"));

        if ("Manager".equals(role) || "HR".equals(role)){
            //登陆者为管理员或HR
            chain.doFilter(req, resp);
        }else {
            //返回登录界面
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect("../../login.jsp");
        }

    }

    public void init(FilterConfig config) throws ServletException {

    }

}

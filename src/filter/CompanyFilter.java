package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/inner/manager/company/*")
public class CompanyFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request=(HttpServletRequest)req;
        HttpSession session=request.getSession();
        String role=String.valueOf(session.getAttribute("role"));

        if ("Manager".equals(role)){
            //登陆者为管理员
            chain.doFilter(req, resp);
        }else {
            //访问者不是管理员
            //返回登录界面
            HttpServletResponse response = (HttpServletResponse) resp;
            response.sendRedirect("../../../login.jsp");
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

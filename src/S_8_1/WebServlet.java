package S_8_1;

import javax.servlet.*;
import java.io.IOException;
import java.io.PrintWriter;

public class WebServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        PrintWriter pwt=servletResponse.getWriter();
        pwt.print("mignrisoft");
        pwt.close();
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}

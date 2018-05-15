package S_9_1;

import javax.servlet.*;
import java.io.IOException;

public class CharactorFilter implements Filter {
    private String encoding;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (encoding!=null){
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setContentType("text/html;charset="+encoding);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding=filterConfig.getInitParameter("encoding");
    }

    @Override
    public void destroy() {
        encoding=null;
    }
}

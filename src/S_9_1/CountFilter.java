package S_9_1;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CountFilter implements Filter {
    private int count;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //服务器重启之后会导致数据还原,此处应该做持久化处理保存数据
        String param=filterConfig.getInitParameter("count");
        count=Integer.valueOf(param);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        count++;
        //强转类型
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        //获取context
        ServletContext context=req.getSession().getServletContext();
        //设置计数
        context.setAttribute("count",count);
        //传递
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}

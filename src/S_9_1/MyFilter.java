package S_9_1;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器
 */
public class MyFilter implements Filter {
    /**
     * 初始化方法
     *
     * @param filterConfig '
     * @throws ServletException '
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //初始化处理
    }

    /**
     * 过滤处理方法
     *
     * @param servletRequest  '
     * @param servletResponse '
     * @param filterChain     '
     * @throws IOException      '
     * @throws ServletException '
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //过滤处理
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        //释放资源
    }
}

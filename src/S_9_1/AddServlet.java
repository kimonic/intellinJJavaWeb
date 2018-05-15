package S_9_1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddServlet extends HttpServlet {
    private static final long serialVersionUID=1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doGet(req, resp);
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
        PrintWriter out=resp.getWriter();
        String id=req.getParameter("id");
        String name=req.getParameter("name");
        String author=req.getParameter("author");
        String price=req.getParameter("price");
        //<hr>定义一条水平线
        out.print("<h2>图书信息添加成功</h2><hr>");
        out.print("图书编号:"+id+"<br>");
        out.print("图书名称:"+name+"<br>");
        out.print("作者:"+author+"<br>");
        out.print("价格:"+price+"<br>");
        out.flush();
        out.close();
    }
}

package S_20_1;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(value = "/ManagerXmlServlet")
public class ManagerXmlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        String action = req.getParameter("action");
        if ("query".equals(action)) {
            query(req, resp);
        } else if ("modify_query".equals(action)) {
            modify_query(req, resp);
        } else if ("del".equals(action)) {
            del(req, resp);
        } else if ("clearAll".equals(action)) {
            clearAll(req, resp);
        }
    }

    private void clearAll(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void del(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void modify_query(HttpServletRequest req, HttpServletResponse resp) {

    }

    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=GBK"); // 设置响应的编码
        String fileURL = req.getRealPath("/xml/placard.xml"); // 获取XML文件的路径
        File file = new File(fileURL);
        Document document = null; // 声明Document对象
        Element placard = null; // 声明Element对象
        List list = null; // 声明List对象
        String description = ""; // 定义保存描述信息的变量
        String createTime = ""; // 定义保存创建日期的变量
        if (file.exists()) { // 判断文件是否存在，如果存在，则获取该文件
            SAXReader reader = new SAXReader(); // 实例化SAXReader对象
            //此处不设置gbk,则不能正常读取xml文件
            reader.setEncoding("GBK");
            try {
                document = reader.read(new File(fileURL));// 获取XML文件对应的XML文档对象
                placard = document.getRootElement(); // 获取根节点
                List list_item = placard.elements("info");
                description = placard.element("description").getText(); // 获取描述信息
                createTime = placard.element("createTime").getText(); // 获取创建日期
                int id = 0;
                String title = ""; // 标题
                String content = ""; // 内容
                String pubDate = ""; // 发布日期
                if (list_item.size() > 0) {
                    list = new ArrayList();
                }
                for (int i = list_item.size(); i > 0; i--) {
                    PlacardForm f = new PlacardForm();
                    Element item = (Element) list_item.get(i - 1);
                    id = Integer.parseInt(item.attribute("id").getValue()); // 获取ID属性
                    f.setId(id);
                    if (null != item.element("title").getText()) {
                        title = item.element("title").getText(); // 获取标题
                    } else {
                        title = "暂无标题";
                    }
                    f.setTitle(title);
                    if (null != item.element("content").getText()) {
                        content = item.element("content").getText(); // 获取标题
                    } else {
                        content = "暂无内容";
                    }
                    f.setContent(content);
                    // 获取发布日期
                    if (null != item.element("pubDate").getText()) {
                        pubDate = item.element("pubDate").getText(); // 获取发布日期
                    }
                    f.setPubDate(pubDate);
                    list.add(f);
                }
                document.clearContent(); // 释放资源
            } catch (DocumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        req.setAttribute("createTime", createTime); // 保存创建日期
        req.setAttribute("description", description); // 保存描述信息
        req.setAttribute("rssContent", list); // 保存公告列表
        //F:\intellinj\JavaJspDevelopment\web\S_20_1\placardList.jsp
        req.getRequestDispatcher("S_20_1/placardList.jsp").forward(req,
                resp);


    }
}

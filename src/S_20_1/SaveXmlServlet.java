package S_20_1;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 保存xml文件的服务
 */
@WebServlet(value = "/SaveXmlServlet")
public class SaveXmlServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        String fileUrl = req.getRealPath("/xml/placard.xml");
        File file=new File(fileUrl);
        String title=req.getParameter("title");
        String content=req.getParameter("content");
        Document document=null;
        Element placard=null;
        DateFormat dateFormat=new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        if (!file.exists()){
            document=DocumentHelper.createDocument();
            placard=DocumentHelper.createElement("placard");
            document.setRootElement(placard);
            placard.addAttribute("version","1.0");
            Element description=placard.addElement("description");
            description.setText("公告栏");
            Element createTime=placard.addElement("createTime");
            createTime.setText("创建于"+dateFormat.format(new Date()));

        }else {
            SAXReader saxReader=new SAXReader();
            //org.dom4j.DocumentException: 1 字节的 UTF-8 序列的字节 1 无效
            //设置后该异常解决,utf-8不行
//            saxReader.setEncoding("UTF-8");
            saxReader.setEncoding("GBK");
            try {
                System.out.println("------------"+fileUrl);
                document=saxReader.read(new File(fileUrl));
                placard=document.getRootElement();
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        if (placard!=null){
            String id=String.valueOf(placard.elements("info").size()+1);
            Element info=placard.addElement("info");
            info.addAttribute("id",id);
            Element title_info=info.addElement("title");
            title_info.setText(title);
            Element item_content=info.addElement("content");
            item_content.addCDATA(content);
            Element pubDate_item=info.addElement("pubDate");
            dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            pubDate_item.setText(dateFormat.format(new Date()));

            OutputFormat format=OutputFormat.createPrettyPrint();
            format.setEncoding("UTF-8");
            XMLWriter writer=new XMLWriter(new FileWriter(fileUrl),format);
            writer.write(document);
            writer.close();
            req.getRequestDispatcher("xml/placard.xml").forward(req,resp);

        }



    }
}

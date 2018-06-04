package S_20_1;

import org.dom4j.*;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TestS201 {
    public static void main(String[] args) {




    }

    private static void parseXML() throws DocumentException {
        //读取指定位置的xml文件
        String fileUrl="F:/documenet.xml";
        SAXReader reader=new SAXReader();
        Document document=reader.read(new File(fileUrl));
        //获取xml对象的根节点
        Element rootElement=document.getRootElement();
        //获取节点唯一的子节点
        Element descripton=rootElement.element("description");
        //获取节点下同名的所有子节点
        List content=rootElement.elements("content");
        //获取id为1的唯一节点
        Node node=rootElement.selectSingleNode("/placard/content[@id='1']");
        //获取所有符合条件的节点
        List list=rootElement.selectNodes("/placard/content");
        //删除子节点
        if (node!=null){
            rootElement.remove(node);
        }

        //删除全部符合条件的子节点
        content.clear();
    }


    /**创建并保存document*/
    private static void createAndSaveDocument() throws IOException{
        //直接创建xml文档
        Document document=DocumentHelper.createDocument();
        //创建只有一个根节点的xml文档
        Element element=DocumentHelper.createElement("placard");
        document.setRootElement(element);
        //为xml的节点添加注释
        element.addComment("这是一个根节点!");
        //为xml节点添加属性
        element.addAttribute("version","1.0");
        //为xml的节点创建子节点
        Element element1=element.addElement("description");
        //设置子节点的内容
        element1.setText("公告栏");
        //指定CDATA中的文本内容为节点内容
        Element content=element.addElement("content");
        content.addCDATA("心中的鹅卵石&童年的梦");
        //为xml指定字符编码
        OutputFormat format=new OutputFormat();
        format.setEncoding("UTF-8");
        //将xml格式话为缩进方式
        OutputFormat format1=OutputFormat.createPrettyPrint();
        //通过工厂模式创建xml文档
        DocumentFactory documentFactory=DocumentFactory.getInstance();
        Document document1=documentFactory.createDocument();

        //将document对象写入xml文件中
        String fileUrl="F:/document.xml";
        XMLWriter xmlWriter=new XMLWriter(new FileWriter(fileUrl));//此处需要抛异常
        //另一种写入方式
        XMLWriter xmlWriter1=new XMLWriter(new FileWriter(fileUrl),format);//此处需要抛异常
        xmlWriter.write(document);
        xmlWriter.close();



        //将document输出到控制台
        XMLWriter xmlWriter2=new XMLWriter(System.out,format1);//此处需要抛异常
        xmlWriter2.write(document);
//        //将document输出到浏览器
//        XMLWriter xmlWriter3=new XMLWriter(out,format);//此处需要抛异常
//        xmlWriter3.write(document);

    }
}

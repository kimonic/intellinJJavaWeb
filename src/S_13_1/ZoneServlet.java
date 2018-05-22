package S_13_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@SuppressWarnings("Duplicates")
@WebServlet(value = "/ZoneServlet")
public class ZoneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        System.out.println("action:"+action);
        if ("getProvince".equals(action)) {
            this.getProvince(req, resp);
        } else if ("getCity".equals(action)) {
            this.getCity(req, resp);
        }
    }

    private void getCity(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        StringBuilder result = new StringBuilder();
//        CityMap cityMap = new CityMap();
//        Map<String, String[]> map = cityMap.model;
//        Set<String> set = map.keySet();
//        Iterator it = set.iterator();
//        while (it.hasNext()) {
//            result.append(it.next()).append(",");
//        }
        String province= null;
        try {
            province = URLDecoder.decode(req.getParameter("province"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html");
        System.out.println(province);
        try {
            String rs="";
            PrintWriter out=resp.getWriter();
            if ("山东".equals(province)){
                rs="济南,青岛,潍坊,威海,章丘,";
            }else {
                rs="深圳,广州,惠州,东莞,韶关,";
            }
            System.out.println(rs);

//            String rs=result.toString();
            out.print(rs.substring(0,rs.length()-1));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("Duplicates")
    private void getProvince(HttpServletRequest req, HttpServletResponse resp) {
        resp.setCharacterEncoding("UTF-8");
        String selProvince=req.getParameter("parProvince");
        StringBuilder result = new StringBuilder();
//        CityMap cityMap = new CityMap();
////        Map<String, String[]> map = cityMap.model;
////        String[] arrCity=map.get(selProvince);
////        for (int i = 0; i < arrCity.length; i++) {
////            result.append(arrCity[i]).append(",");
////        }

        resp.setContentType("text/html");
        try {
            PrintWriter out=resp.getWriter();
//            String rs=result.toString();
            String rs="山东,山西,北京,广东,上海,";
            out.print(rs.substring(0,rs.length()-1));
//            out.print(rs.substring(0,rs.length()-2));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

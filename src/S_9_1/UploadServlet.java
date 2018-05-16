package S_9_1;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/uploadServlet")
@MultipartConfig(location = "E:/tmp")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String path = getServletContext().getRealPath("/");
        Part p = req.getPart("file1");
        if (p.getContentType().contains("image")) {
            String fname1 = p.getSubmittedFileName();
            int path_idx = fname1.lastIndexOf("//") + 1;
            String fname2 = fname1.substring(path_idx);
            String realPath = path + "/upload/";
            checkFolder(realPath);
            System.out.println(realPath+ fname2);
            p.write(path + "/upload/" + fname2);
            out.write("文件上传成功!");
        } else {
            out.write("请选择图片文件!");
        }


    }


    /**
     * 创建存放上传文件的文件夹，如果不存在则自动创建
     *
     * @param realPath '
     */
    private void checkFolder(String realPath) {
        File file = new File(realPath);
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.mkdirs();
        }
    }
}

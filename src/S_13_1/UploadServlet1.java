package S_13_1;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;

@WebServlet(value = "/UploadServlet1")
@MultipartConfig(location = "E:/tmp")
public class UploadServlet1 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        uploadFile(req, resp);
    }

    public void uploadFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("------------------1");
        resp.setContentType("text/html");
        HttpSession session = req.getSession();//定义上传进度的session变量
        session.setAttribute("progressBar", 0);
        String error = "";
        int maxSize = 50 * 1024 * 1024;//单个上传文件大小的上限
        System.out.println("------------------2");
        DiskFileItemFactory factory = new DiskFileItemFactory();//基于磁盘文件项目创建一个工厂对象
        ServletFileUpload upload = new ServletFileUpload(factory);//创建一个新的文件上传对象
        System.out.println("------------------3");
        try {
          /**  PrintWriter out = resp.getWriter();
            Part p = req.getPart("file");
            String path = getServletContext().getRealPath("/");
            long size = p.getSize();
            System.out.println("上传文件的大小是:"+size);
            if (size > maxSize) {
                out.write("上传的文件不能超过50M!");
            } else {
                String fname1 = p.getSubmittedFileName();
                int path_idx = fname1.lastIndexOf("//") + 1;
                String fname2 = fname1.substring(path_idx);
                String realPath = path + "/upload/";
//                p.write(path + "/upload/" + fname2);
//                out.write("文件上传成功!");

                File file=new File(path + "/upload/" + fname2);
                InputStream is = p.getInputStream();
                int buffer = 1024;//构建缓冲区
                int length = 0;
                byte[] b = new byte[buffer];
                double percent = 0;
                FileOutputStream fos = new FileOutputStream(file);
                while ((length = is.read(b)) != -1) {
                    percent += length / (double) size * 100D;//计算上传文件的百分比
                    fos.write(b, 0, length);
                    session.setAttribute("progressBar", Math.round(percent));
                }
                out.write("文件上传成功!");
                fos.close();//关闭文件流
                Thread.sleep(1000);//线程休眠1s
            }
           */

          upload.setProgressListener(new ProgressListener() {
              @Override
              public void update(long pBytesRead, long pContentLength, int pItems) {
                  System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
              }
          });

            System.out.println("------------------4???");
            List items = upload.parseRequest(req);//解析上传请求
            System.out.println("------------------4!!!!");
            Iterator itr = items.iterator();//枚举方法
            while (itr.hasNext()) {
                System.out.println("------------------4");
                FileItem item = (FileItem) itr.next();//获取FileItem对象
                if (!item.isFormField()) {//判断是否为文件域
                    System.out.println("------------------5");
                    if (item.getName() != null && !"".equals(item.getName())) {//判断是否选择了文件
                        System.out.println("------------------6");
                        long upFileSize = item.getSize();//上传文件的大小
                        String fileName = item.getName();//获取文件名
                        System.out.println("------------------1"+upFileSize);
                        System.out.println("------------------1"+fileName);
                        if (upFileSize > maxSize) {
                            error = "您上传的文件太大, 请不要超过50M!";
                            break;
                        }
                        //此时文件暂存在服务器的内存中
                        File tempFlie=new File(fileName);//构建临时文件对象
                        //获取根目录对应的真实物理路径
                        File file=new File(req.getRealPath("/upload"),tempFlie.getName());
                        InputStream is=item.getInputStream();
                        int buffer=1024;//构建缓冲区
                        int length=0;
                        byte[] b=new byte[buffer];
                        double percent=0;
                        FileOutputStream fos=new FileOutputStream(file);
                        while ((length=is.read(b))!=-1){
                            percent+=length/(double)upFileSize*100D;//计算上传文件的百分比
                            fos.write(b,0,length);
                            session.setAttribute("progressBar",Math.round(percent));
                        }

                        fos.close();//关闭文件流
                        Thread.sleep(1000);//线程休眠1s

                    }else {
                        error="没有选择上传文件!";
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            error = "上传文件出现错误:" + e.getMessage();
        }
        if (!"".equals(error)) {
            req.setAttribute("error", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else {
            req.setAttribute("result", "文件上传成功!");
            req.getRequestDispatcher("upfile_deal.jsp").forward(req, resp);
        }
    }

}
/**Java Web下接收文件常基于fileupload插件实现，其一般代码如下：
 index.html

 <!DOCTYPE html>
 <html lang="en">
 <head>
 <meta charset="UTF-8"/>
 <title>Title</title>
 </head>
 <body>
 <form action="/test/file" enctype="multipart/form-data"
 method="post">
 上传用户：<input type="text" name="username"/><br/>
 上传文件：<input type="file" name="file2"/><br/>
 <input type="submit" value="提交"/>
 </form>
 </body>
 </html>
 对应的后台Controller代码:

 @RequestMapping(value = "/test/file")
 @ResponseBody
 public String file (HttpServletRequest request,HttpServletResponse response) throws IOException{
 response.setContentType("application/json;charset=utf-8");
 try{
 //使用Apache文件上传组件处理文件上传步骤：
 //1、创建一个DiskFileItemFactory工厂
 DiskFileItemFactory factory = new DiskFileItemFactory();
 //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
 factory.setSizeThreshold(1024*100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
 /*//**设置上传时生成的临时文件的保存目录
        factory.setRepository(tmpFile);*//**
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //监听文件上传进度
        upload.setProgressListener(new ProgressListener(){
public void update(long pBytesRead, long pContentLength, int arg2) {
        System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
        }
        });
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        //3、判断提交上来的数据是否是上传表单的数据
        if(!ServletFileUpload.isMultipartContent(request)){
        //按照传统方式获取数据
        return;
        }

        //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
        upload.setFileSizeMax(1024*1024*10);
        //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
        upload.setSizeMax(1024*1024*30);
        //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
        List<FileItem> list = upload.parseRequest(request);
        for(FileItem item : list){
        //如果fileitem中封装的是普通输入项的数据
        if(item.isFormField()){
        String name = item.getFieldName();
        //解决普通输入项的数据的中文乱码问题
        String value = item.getString("UTF-8");
        //value = new String(value.getBytes("iso8859-1"),"UTF-8");
        System.out.println(name + "=" + value);
        }else{//如果fileitem中封装的是上传文件
        //得到上传的文件名称，
        String filename = item.getName();
        System.out.println(filename);
        if(filename==null || filename.trim().equals("")){
        continue;
        }
        //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
        //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
        filename = filename.substring(filename.lastIndexOf("\\")+1);
        //得到上传文件的扩展名
        String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
        filename = filename.substring(0,filename.lastIndexOf("."));
        //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
        System.out.println("上传的文件的扩展名是："+fileExtName);
        //保存文件
        FileManager fileManager = new FileManager();
        if(FileManager.ERROR.equals(fileManager.save(filename,fileExtName,"song",item.getInputStream()))){
        return JsonUtil.statusResponse(1,"上传文件失败",null);
        }
        }
        }
        }catch (FileUploadBase.FileSizeLimitExceededException e) {
        e.printStackTrace();
        return JsonUtil.statusResponse(1,"单个文件超出最大值！！！",null);
        }catch (FileUploadBase.SizeLimitExceededException e) {
        e.printStackTrace();
        return JsonUtil.statusResponse(1,"上传文件的总的大小超出限制的最大值！！！",null);
        }catch (Exception e) {
        e.printStackTrace();
        return JsonUtil.statusResponse(1,"其他异常，上传失败！！！",null);
        }
        return JsonUtil.statusResponse(0,"上传文件成功",fileManager.getFileURI(filename,fileExtName));
        }
        我是在SpringBoot下测试时，发现的该问题，即在解析请求时List list = upload.parseRequest(request);得到的list size=0,也就是根本没有得到文件数据。我在网上搜索该问题的解决方法，大致有以下两种：
        （1）原因在于Spring的配置文件中已经配置了MultipartResolver，导致文件上传请求已经被预处理过了，所以此处解析文件列表为空，对应的做法是删除该段配置。
        （2）认为是structs的过滤器导致请求已被预处理，所以也要修改对应过滤器的配置。
        然而，在SpringBoot下，上述两种解决方法不可能做到，因为SpringBoot的相关配置都是自己完成的，根本没有显示的配置文件。况且以上两种解决方法，修改配置文件可能影响整个工程的其他部分，所以得另寻方案。
        我通过断点调试该Controller代码，发现传入的参数HttpServletRequest实例已经为StandardMultipartHttpServletRequest 对象了，且其结构中包含整个form表单的所有字段信息，我就想，区别于网上已有的两种解决方案，总是想避免这种预处理，何不就利用这种预处理，来简化自己的代码结构呢？于是就有了下面的解决代码。其方法很简单，就是对传入的request做强制转型，从而可以根据StandardMultipartHttpServletRequest 实例方法得到相关form表单数据，从而大大简化代码结构，示意如下：

@RequestMapping(value = "/file")
@ResponseBody
public String file (HttpServletRequest request, HttpServletResponse response) throws IOException {
        ...
        try {
        StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
        Iterator<String> iterator = req.getFileNames();
        while (iterator.hasNext()) {
        MultipartFile file = req.getFile(iterator.next());
        String fileNames = file.getOriginalFilename();
        int split = fileNames.lastIndexOf(".");
        //存储文件
        //文件名  fileNames.substring(0,split)
        //文件格式   fileNames.substring(split+1,fileNames.length())
        //文件内容 file.getBytes()
        ...
        }
        }catch (Exception e){
        e.printStackTrace();
        return "fail";
        }
        return "success";
        }
 */
package S_13_1;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadBase;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileCleaningTracker;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@WebServlet(value = "/UploadServlet1")
@MultipartConfig(location = "E:/tmp")
public class UploadServlet1 extends HttpServlet {
    private double percent = 0;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        uploadFile(req, resp);
    }

    public void uploadFile(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");//设置响应类型
        HttpSession session = req.getSession();//定义上传进度的session变量
        session.setAttribute("progressBar", 0);
        String error = "";
        int maxSize = 500*1024 * 1024;//单个上传文件大小的上限
        String savePath = this.getServletContext().getRealPath("/upload");
        String savetempPath = this.getServletContext().getRealPath("/temp");
        System.out.println("savePath的值是:"+savePath);
        System.out.println("savetempPath的值是:"+savePath);
        File file = new File(savetempPath);
        if (!file.exists()){
            file.mkdirs();
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();//基于磁盘文件项目创建一个工厂对象
        factory.setSizeThreshold(1024 * 100);//设置缓冲区大小
        factory.setRepository(file);//设置缓冲区目录
        //设置缓存文件追踪
        factory.setFileCleaningTracker(new FileCleaningTracker());
        ServletFileUpload upload = new ServletFileUpload(factory);//创建一个新的文件上传对象
        //监听上传进度
        upload.setProgressListener(new ProgressListener() {
            @Override
            public void update(long pBytesRead, long pContentLength, int pItems) {
                System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
                percent = (pBytesRead * 100D / pContentLength);
                System.out.println("当前的上传进度是" + percent);
                session.setAttribute("progressBar", Math.round(percent));
            }
        });

        System.out.println("监听执行后立即执行------------------------------");
        try {
            InputStream in=null;
            FileOutputStream out=null;
//
            upload.setFileSizeMax(maxSize);//设置上传文件的最大大小
            upload.setHeaderEncoding("UTF-8");//设置上传文件中文名乱码问题解决
            //设置上传文件总量的最大值(同时上传多个文件的大小总和)
            upload.setSizeMax(maxSize * 5);
//
//            if (!ServletFileUpload.isMultipartContent(req)) {//判断提交上来的数据是否是上传表单的数据
//                return;//按照传统方式获取数据
//            }
//
////            使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
//            //文件完全上传成功后才会调用该方法
            List<FileItem> items = upload.parseRequest(req);//解析上传请求
            System.out.println("items的大小是:"+items.size());
            for (FileItem item : items) {
                //如果fileitem中封装的是普通输入项的数据
                if (item.isFormField()) {
                    String name = item.getFieldName();
                    String value = item.getString("utf-8");
                    System.out.println(name + "=" + value);
                } else {//如果fileitem中封装的是上传文件
                    //得到上传的文件名称，
                    String filename = item.getName();
                    System.out.println("上传文件的名称是:"+filename);
//                    if (filename == null || filename.trim().equals("")) {
//                        continue;
//                    }
                    //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，
                    // 如： c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
                    //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
                    filename = filename.substring(filename.lastIndexOf("//") + 1);
                    //得到上传文件的扩展名
                    String fileExtName = filename.substring(filename.lastIndexOf(".") + 1);
                    //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
                    System.out.println("上传的文件的扩展名是：" + fileExtName);
                    //获取item中的上传文件的输入流
                    in = item.getInputStream();
//                    //得到文件保存的名称
//                    String saveFilename = makeFileName(filename);
//                    //得到文件的保存目录
//                    String realSavePath = makePath(saveFilename, savePath);
                    //创建一个文件输出流,也就是将缓冲区中的文件保存到的目录
                    out = new FileOutputStream(savePath+"/"+filename);
                    System.out.println("savePath+filename:"+savePath+filename);
                    //创建一个缓冲区
                    byte buffer[] = new byte[1024];
                    //判断输入流中的数据是否已经读完的标识
                    int len = 0;
                    //循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
                    while ((len = in.read(buffer)) > 0) {
                        //使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
                        out.write(buffer, 0, len);
                    }
                    //删除处理文件上传时生成的临时文件
                    //临时文件删除不成功为解决?????
                    item.delete();


                    System.out.println("factory.getFileCleaningTracker().getTrackCount():"+factory.getFileCleaningTracker().getTrackCount());


                }
            }
            error = "文件上传成功！";
            //关闭输入流
            in.close();
//            关闭输出流
            out.close();

//            message = "文件上传成功！";
//            Iterator itr = items.iterator();//枚举方法
//            while (itr.hasNext()) {
//                FileItem item = (FileItem) itr.next();//获取FileItem对象
//                if (!item.isFormField()) {//判断是否为文件域
//                    if (item.getName() != null && !"".equals(item.getName())) {//判断是否选择了文件
//                        long upFileSize = item.getSize();//上传文件的大小
//                        String fileName = item.getName();//获取文件名
//                        if (upFileSize > maxSize) {
//                            error = "您上传的文件太大, 请不要超过50M!";
//                            break;
//                        }
//                        //此时文件暂存在服务器的内存中
//                        File tempFlie=new File(fileName);//构建临时文件对象
//                        //获取根目录对应的真实物理路径
//                        File file=new File(req.getRealPath("/upload"),tempFlie.getName());

//                        InputStream is=item.getInputStream();
//                        int buffer=1024;//构建缓冲区
//                        int length=0;
//                        byte[] b=new byte[buffer];
//                        FileOutputStream fos=new FileOutputStream(file);
//                        while ((length=is.read(b))!=-1){
//                            percent+=length/(double)upFileSize*100D;//计算上传文件的百分比
//                            fos.write(b,0,length);
//                            session.setAttribute("progressBar",Math.round(percent));
//                        }
//
//                        fos.close();//关闭文件流
//                        Thread.sleep(1000);//线程休眠1s

//                    }else {
//                        error="没有选择上传文件!";
//                    }
//                }
//            }
//
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            e.printStackTrace();
            req.setAttribute("message", "单个文件超出最大值！！！");
            req.getRequestDispatcher("/13-1/showprogress.jsp").forward(req, resp);
            return;
        }catch (FileUploadBase.SizeLimitExceededException e) {
            e.printStackTrace();
            req.setAttribute("message", "上传文件的总的大小超出限制的最大值！！！");
            req.getRequestDispatcher("/13-1/showprogress.jsp").forward(req, resp);
            return;
        }catch (Exception e) {
            error= "文件上传失败！";
            e.printStackTrace();
        }
        req.setAttribute("message",error);
        req.getRequestDispatcher("/13-1/showprogress.jsp").forward(req, resp);
    }



//        catch (Exception e) {
//            e.printStackTrace();
//            error = "上传文件出现错误:" + e.getMessage();
//        }
//        if (!"".equals(error)) {
//            req.setAttribute("error", error);
//            req.getRequestDispatcher("error.jsp").forward(req, resp);
//        } else {
//            req.setAttribute("result", "文件上传成功!");
//            req.getRequestDispatcher("upfile_deal.jsp").forward(req, resp);
//        }
//    }




/**
 * @param filename 文件的原始名称
 * @return uuid+"_"+文件的原始名称
 * @Method: makeFileName
 * @Description: 生成上传文件的文件名，文件名以：uuid+"_"+文件的原始名称
 * @Anthor:孤傲苍狼
 */
private String makeFileName(String filename){ //2.jpg
        //为防止文件覆盖的现象发生，要为上传文件产生一个唯一的文件名
        return UUID.randomUUID().toString()+"_"+filename;
        }

/**
 * 为防止一个目录下面出现太多文件，要使用hash算法打散存储
 *
 * @param filename 文件名，要根据文件名生成存储目录
 * @param savePath 文件存储路径
 * @return 新的存储目录
 * @Method: makePath
 * @Description:
 * @Anthor:孤傲苍狼
 */
private String makePath(String filename,String savePath){
        //得到文件名的hashCode的值，得到的就是filename这个字符串对象在内存中的地址
        int hashcode=filename.hashCode();
        int dir1=hashcode&0xf; //0--15
        int dir2=(hashcode&0xf0)>>4; //0-15
        //构造新的保存目录
        String dir=savePath+"//"+dir1+"//"+dir2; //upload\2\3 upload\3\5
        //File既可以代表文件也可以代表目录
        File file=new File(dir);
        //如果目录不存在
        if(!file.exists()){
        //创建目录
        file.mkdirs();
        }
        return dir;
        }


        }
/**
 * Java Web下接收文件常基于fileupload插件实现，其一般代码如下：
 * index.html
 * <p>
 * <!DOCTYPE html>
 * <html lang="en">
 * <head>
 * <meta charset="UTF-8"/>
 * <title>Title</title>
 * </head>
 * <body>
 * <form action="/test/file" enctype="multipart/form-data"
 * method="post">
 * 上传用户：<input type="text" name="username"/><br/>
 * 上传文件：<input type="file" name="file2"/><br/>
 * <input type="submit" value="提交"/>
 * </form>
 * </body>
 * </html>
 * 对应的后台Controller代码:
 *
 * @RequestMapping(value = "/test/file")
 * @ResponseBody public String file (HttpServletRequest request,HttpServletResponse response) throws IOException{
 * response.setContentType("application/json;charset=utf-8");
 * try{
 * //使用Apache文件上传组件处理文件上传步骤：
 * //1、创建一个DiskFileItemFactory工厂
 * DiskFileItemFactory factory = new DiskFileItemFactory();
 * //设置工厂的缓冲区的大小，当上传的文件大小超过缓冲区的大小时，就会生成一个临时文件存放到指定的临时目录当中。
 * factory.setSizeThreshold(1024*100);//设置缓冲区的大小为100KB，如果不指定，那么缓冲区的大小默认是10KB
 * /设置上传时生成的临时文件的保存目录
 * factory.setRepository(tmpFile);
 * //2、创建一个文件上传解析器
 * ServletFileUpload upload = new ServletFileUpload(factory);
 * //监听文件上传进度
 * upload.setProgressListener(new ProgressListener(){
 * public void update(long pBytesRead, long pContentLength, int arg2) {
 * System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
 * }
 * });
 * //解决上传文件名的中文乱码
 * upload.setHeaderEncoding("UTF-8");
 * //3、判断提交上来的数据是否是上传表单的数据
 * if(!ServletFileUpload.isMultipartContent(request)){
 * //按照传统方式获取数据
 * return;
 * }
 * <p>
 * //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
 * upload.setFileSizeMax(1024*1024*10);
 * //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
 * upload.setSizeMax(1024*1024*30);
 * //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，
 * 每一个FileItem对应一个Form表单的输入项
 * List<FileItem> list = upload.parseRequest(request);
 * for(FileItem item : list){
 * //如果fileitem中封装的是普通输入项的数据
 * if(item.isFormField()){
 * String name = item.getFieldName();
 * //解决普通输入项的数据的中文乱码问题
 * String value = item.getString("UTF-8");
 * //value = new String(value.getBytes("iso8859-1"),"UTF-8");
 * System.out.println(name + "=" + value);
 * }else{//如果fileitem中封装的是上传文件
 * //得到上传的文件名称，
 * String filename = item.getName();
 * System.out.println(filename);
 * if(filename==null || filename.trim().equals("")){
 * continue;
 * }
 * //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
 * //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
 * filename = filename.substring(filename.lastIndexOf("\\")+1);
 * //得到上传文件的扩展名
 * String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
 * filename = filename.substring(0,filename.lastIndexOf("."));
 * //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
 * System.out.println("上传的文件的扩展名是："+fileExtName);
 * //保存文件
 * FileManager fileManager = new FileManager();
 * if(FileManager.ERROR.equals(fileManager.save(filename,fileExtName,"song",item.getInputStream()))){
 * return JsonUtil.statusResponse(1,"上传文件失败",null);
 * }
 * }
 * }
 * }catch (FileUploadBase.FileSizeLimitExceededException e) {
 * e.printStackTrace();
 * return JsonUtil.statusResponse(1,"单个文件超出最大值！！！",null);
 * }catch (FileUploadBase.SizeLimitExceededException e) {
 * e.printStackTrace();
 * return JsonUtil.statusResponse(1,"上传文件的总的大小超出限制的最大值！！！",null);
 * }catch (Exception e) {
 * e.printStackTrace();
 * return JsonUtil.statusResponse(1,"其他异常，上传失败！！！",null);
 * }
 * return JsonUtil.statusResponse(0,"上传文件成功",fileManager.getFileURI(filename,fileExtName));
 * }
 * 我是在SpringBoot下测试时，发现的该问题，即在解析请求时List list = upload.parseRequest(request);
 * 得到的list size=0,也就是根本没有得到文件数据。我在网上搜索该问题的解决方法，大致有以下两种：
 * （1）原因在于Spring的配置文件中已经配置了MultipartResolver，导致文件上传请求已经被预处理过了，
 * 所以此处解析文件列表为空，对应的做法是删除该段配置。
 * （2）认为是structs的过滤器导致请求已被预处理，所以也要修改对应过滤器的配置。
 * 然而，在SpringBoot下，上述两种解决方法不可能做到，因为SpringBoot的相关配置都是自己完成的，
 * 根本没有显示的配置文件。况且以上两种解决方法，修改配置文件可能影响整个工程的其他部分，所以得另寻方案。
 * 我通过断点调试该Controller代码，发现传入的参数HttpServletRequest实例已经为StandardMultipartHttpServletRequest
 * 对象了，且其结构中包含整个form表单的所有字段信息，我就想，区别于网上已有的两种解决方案，总是想避免这种预处理，
 * 何不就利用这种预处理，来简化自己的代码结构呢？于是就有了下面的解决代码。其方法很简单，就是对传入的request做强制转型，
 * 从而可以根据StandardMultipartHttpServletRequest 实例方法得到相关form表单数据，从而大大简化代码结构，示意如下：
 * @RequestMapping(value = "/file")
 * @ResponseBody public String file (HttpServletRequest request, HttpServletResponse response) throws IOException {
 * ...
 * try {
 * StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
 * Iterator<String> iterator = req.getFileNames();
 * while (iterator.hasNext()) {
 * MultipartFile file = req.getFile(iterator.next());
 * String fileNames = file.getOriginalFilename();
 * int split = fileNames.lastIndexOf(".");
 * //存储文件
 * //文件名  fileNames.substring(0,split)
 * //文件格式   fileNames.substring(split+1,fileNames.length())
 * //文件内容 file.getBytes()
 * ...
 * }
 * }catch (Exception e){
 * e.printStackTrace();
 * return "fail";
 * }
 * return "success";
 * }
 * 设置上传时生成的临时文件的保存目录
 * factory.setRepository(tmpFile);
 * //2、创建一个文件上传解析器
 * ServletFileUpload upload = new ServletFileUpload(factory);
 * //监听文件上传进度
 * upload.setProgressListener(new ProgressListener(){
 * public void update(long pBytesRead, long pContentLength, int arg2) {
 * System.out.println("文件大小为：" + pContentLength + ",当前已处理：" + pBytesRead);
 * }
 * });
 * //解决上传文件名的中文乱码
 * upload.setHeaderEncoding("UTF-8");
 * //3、判断提交上来的数据是否是上传表单的数据
 * if(!ServletFileUpload.isMultipartContent(request)){
 * //按照传统方式获取数据
 * return;
 * }
 * <p>
 * //设置上传单个文件的大小的最大值，目前是设置为1024*1024字节，也就是1MB
 * upload.setFileSizeMax(1024*1024*10);
 * //设置上传文件总量的最大值，最大值=同时上传的多个文件的大小的最大值的和，目前设置为10MB
 * upload.setSizeMax(1024*1024*30);
 * //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，
 * 每一个FileItem对应一个Form表单的输入项
 * List<FileItem> list = upload.parseRequest(request);
 * for(FileItem item : list){
 * //如果fileitem中封装的是普通输入项的数据
 * if(item.isFormField()){
 * String name = item.getFieldName();
 * //解决普通输入项的数据的中文乱码问题
 * String value = item.getString("UTF-8");
 * //value = new String(value.getBytes("iso8859-1"),"UTF-8");
 * System.out.println(name + "=" + value);
 * }else{//如果fileitem中封装的是上传文件
 * //得到上传的文件名称，
 * String filename = item.getName();
 * System.out.println(filename);
 * if(filename==null || filename.trim().equals("")){
 * continue;
 * }
 * //注意：不同的浏览器提交的文件名是不一样的，有些浏览器提交上来的文件名是带有路径的，如：  c:\a\b\1.txt，而有些只是单纯的文件名，如：1.txt
 * //处理获取到的上传文件的文件名的路径部分，只保留文件名部分
 * filename = filename.substring(filename.lastIndexOf("\\")+1);
 * //得到上传文件的扩展名
 * String fileExtName = filename.substring(filename.lastIndexOf(".")+1);
 * filename = filename.substring(0,filename.lastIndexOf("."));
 * //如果需要限制上传的文件类型，那么可以通过文件的扩展名来判断上传的文件类型是否合法
 * System.out.println("上传的文件的扩展名是："+fileExtName);
 * //保存文件
 * FileManager fileManager = new FileManager();
 * if(FileManager.ERROR.equals(fileManager.save(filename,fileExtName,"song",item.getInputStream()))){
 * return JsonUtil.statusResponse(1,"上传文件失败",null);
 * }
 * }
 * }
 * }catch (FileUploadBase.FileSizeLimitExceededException e) {
 * e.printStackTrace();
 * return JsonUtil.statusResponse(1,"单个文件超出最大值！！！",null);
 * }catch (FileUploadBase.SizeLimitExceededException e) {
 * e.printStackTrace();
 * return JsonUtil.statusResponse(1,"上传文件的总的大小超出限制的最大值！！！",null);
 * }catch (Exception e) {
 * e.printStackTrace();
 * return JsonUtil.statusResponse(1,"其他异常，上传失败！！！",null);
 * }
 * return JsonUtil.statusResponse(0,"上传文件成功",fileManager.getFileURI(filename,fileExtName));
 * }
 * 我是在SpringBoot下测试时，发现的该问题，即在解析请求时List list = upload.parseRequest(request);
 * 得到的list size=0,也就是根本没有得到文件数据。我在网上搜索该问题的解决方法，大致有以下两种：
 * （1）原因在于Spring的配置文件中已经配置了MultipartResolver，导致文件上传请求已经被预处理过了，
 * 所以此处解析文件列表为空，对应的做法是删除该段配置。
 * （2）认为是structs的过滤器导致请求已被预处理，所以也要修改对应过滤器的配置。
 * 然而，在SpringBoot下，上述两种解决方法不可能做到，因为SpringBoot的相关配置都是自己完成的，
 * 根本没有显示的配置文件。况且以上两种解决方法，修改配置文件可能影响整个工程的其他部分，所以得另寻方案。
 * 我通过断点调试该Controller代码，发现传入的参数HttpServletRequest实例已经为StandardMultipartHttpServletRequest
 * 对象了，且其结构中包含整个form表单的所有字段信息，我就想，区别于网上已有的两种解决方案，总是想避免这种预处理，
 * 何不就利用这种预处理，来简化自己的代码结构呢？于是就有了下面的解决代码。其方法很简单，就是对传入的request做强制转型，
 * 从而可以根据StandardMultipartHttpServletRequest 实例方法得到相关form表单数据，从而大大简化代码结构，示意如下：
 * @RequestMapping(value = "/file")
 * @ResponseBody public String file (HttpServletRequest request, HttpServletResponse response) throws IOException {
 * ...
 * try {
 * StandardMultipartHttpServletRequest req = (StandardMultipartHttpServletRequest) request;
 * Iterator<String> iterator = req.getFileNames();
 * while (iterator.hasNext()) {
 * MultipartFile file = req.getFile(iterator.next());
 * String fileNames = file.getOriginalFilename();
 * int split = fileNames.lastIndexOf(".");
 * //存储文件
 * //文件名  fileNames.substring(0,split)
 * //文件格式   fileNames.substring(split+1,fileNames.length())
 * //文件内容 file.getBytes()
 * ...
 * }
 * }catch (Exception e){
 * e.printStackTrace();
 * return "fail";
 * }
 * return "success";
 * }
 *//**设置上传时生成的临时文件的保存目录
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
 //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，
 每一个FileItem对应一个Form表单的输入项
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
 我是在SpringBoot下测试时，发现的该问题，即在解析请求时List list = upload.parseRequest(request);
 得到的list size=0,也就是根本没有得到文件数据。我在网上搜索该问题的解决方法，大致有以下两种：
 （1）原因在于Spring的配置文件中已经配置了MultipartResolver，导致文件上传请求已经被预处理过了，
 所以此处解析文件列表为空，对应的做法是删除该段配置。
 （2）认为是structs的过滤器导致请求已被预处理，所以也要修改对应过滤器的配置。
 然而，在SpringBoot下，上述两种解决方法不可能做到，因为SpringBoot的相关配置都是自己完成的，
 根本没有显示的配置文件。况且以上两种解决方法，修改配置文件可能影响整个工程的其他部分，所以得另寻方案。
 我通过断点调试该Controller代码，发现传入的参数HttpServletRequest实例已经为StandardMultipartHttpServletRequest
 对象了，且其结构中包含整个form表单的所有字段信息，我就想，区别于网上已有的两种解决方案，总是想避免这种预处理，
 何不就利用这种预处理，来简化自己的代码结构呢？于是就有了下面的解决代码。其方法很简单，就是对传入的request做强制转型，
 从而可以根据StandardMultipartHttpServletRequest 实例方法得到相关form表单数据，从而大大简化代码结构，示意如下：

 @RequestMapping(value = "/file")
 @ResponseBody public String file (HttpServletRequest request, HttpServletResponse response) throws IOException {
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
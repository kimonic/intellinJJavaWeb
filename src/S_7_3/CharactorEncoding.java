package S_7_3;

import java.io.UnsupportedEncodingException;

public class CharactorEncoding {
    public CharactorEncoding() {
    }

    public String toString(String str){
        String text="";
        if (str!=null&&!"".equals(str)){
            try {
                //对字符串按照指定编码再次进行编码
                text=new String(str.getBytes("ISO-8859-1"),"UTF-8");
            }catch (UnsupportedEncodingException e){
                e.printStackTrace();
            }
        }
        //返回重新编码后的字符串
        return text;
    }
}

package S_7_3;

public class Convert {
    public String arr2Str(String[] arr){
        StringBuffer sb=new StringBuffer();
        if (arr!=null&&arr.length>0){
            for (String s:arr) {
                sb.append(s).append(",");
            }
            if (sb.length()>0){
                sb.deleteCharAt(-1);
            }
        }
        return sb.toString();
    }
}
